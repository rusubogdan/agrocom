package com.agrocom.controller;

import com.agrocom.helpers.MinifyUtil;
import com.agrocom.model.Infield;
import com.agrocom.model.Society;
import com.agrocom.model.User;
import com.agrocom.service.InfieldService;
import com.agrocom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/infield")
public class InfieldController {

    @Autowired
    InfieldService infieldService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView serverViewInfield(@PathVariable Long id,
                                          HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("viewInfield");

        // todo if society does not contain infield

        Infield infield = infieldService.getInfield(id, true);

        if (infield == null) {
            return new ModelAndView("redirect:/home?selectedMenuItem=infields");
        }
        infield = MinifyUtil.minifyInfield(infield);
        mv.addObject("infield", infield);

        return mv;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView serveEditPage(@PathVariable Long id,
                                      HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("editInfield");

        Infield infield = infieldService.getInfield(id, true);

        if (infield == null) {
            return new ModelAndView("redirect:/home?selectedMenuItem=infields");
        }

        Society society = (Society) request.getSession().getAttribute("society");

        List<User> landlords = userService.getLandlordsBySociety(society);

        landlords = MinifyUtil.minifyUsers(landlords);

        Long landLordId = -1l;
        if (infield.getTenant() != null) {
            landLordId = infield.getTenant().getUserId();
        }

        infield = MinifyUtil.minifyInfield(infield);

        mv.addObject("infield", infield);
        mv.addObject("landLords", landlords);
        mv.addObject("landLordId", landLordId);

        return mv;
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam Long infieldId,
                             @RequestParam Long landLordId,
                             @RequestParam String locationCode, @RequestParam String county,
                             @RequestParam String village,
                             @RequestParam Double areaHa,
                             @RequestParam(required = false, defaultValue = " ") String fiveYearsAgo,
                             @RequestParam(required = false, defaultValue = " ") String fourYearsAgo,
                             @RequestParam(required = false, defaultValue = " ") String threeYearsAgo,
                             @RequestParam(required = false, defaultValue = " ") String twoYearsAgo,
                             @RequestParam String lastYear,
                             RedirectAttributes redirectAttributes) {
        Infield infield = infieldService.getInfield(infieldId, true);

        if (infield == null) {
            return new ModelAndView("redirect:/home?selectedMenuItem=infields");
        }

        Infield infieldByCode = infieldService.getInfieldByCode(locationCode);

        if (infieldByCode != null) {
            redirectAttributes.addAttribute("error", "Unexisting landlord");
            return new ModelAndView("redirect:/edit/" + infieldId);
        }

        User landLord = null;

        if (landLordId == null) {
            infield.setTenant(null);
        } else {
            landLord = userService.getUser(landLordId, true);
            if (landLord == null) {
                redirectAttributes.addAttribute("error", "Unexisting landlord");
                return new ModelAndView("redirect:/edit/" + infieldId);
            }
        }

        infield.setLocationCode(locationCode);
        infield.setTenant(landLord);
        infield.setAreaHa(areaHa);
        infield.setCounty(county);
        infield.setVillage(village);
        infield.setLastYear(lastYear);

        if (twoYearsAgo == null || twoYearsAgo.equals("")) infield.setTwoYearsAgo(null);
        else infield.setTwoYearsAgo(twoYearsAgo);

        if (threeYearsAgo == null || threeYearsAgo.equals("")) infield.setThreeYearsAgo(null);
        else infield.setThreeYearsAgo(threeYearsAgo);

        if (fourYearsAgo == null || fourYearsAgo.equals("")) infield.setFourYearsAgo(null);
        else infield.setFourYearsAgo(fourYearsAgo);

        if (fiveYearsAgo == null || fiveYearsAgo.equals("")) infield.setFiveYearsAgo(null);
        else infield.setFiveYearsAgo(fiveYearsAgo);

        infieldService.updateInfield(infield);

        return new ModelAndView("redirect:/infield/view/" + infieldId);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView serveAddPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("addInfield");

        Society society = (Society) request.getSession().getAttribute("society");
        List<User> landLords = userService.getLandlordsBySociety(society);

        landLords = MinifyUtil.minifyUsers(landLords);

        mv.addObject("landLords", landLords);

        return mv;
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public ModelAndView add( @RequestParam Long landLordSelectAdd,
                             @RequestParam String locationCode, @RequestParam String county,
                             @RequestParam String village,
                             @RequestParam Double areaHa,
                             @RequestParam(required = false) String fiveYearsAgo,
                             @RequestParam(required = false) String fourYearsAgo,
                             @RequestParam(required = false) String threeYearsAgo,
                             @RequestParam(required = false) String twoYearsAgo,
                             @RequestParam String lastYear,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request) {

        if (landLordSelectAdd == -1l) {
            return new ModelAndView("redirect:/home?selectedMenuItem=infields");
        }

        User landLord = userService.getUser(landLordSelectAdd, true);

        Infield infield = infieldService.getInfieldByCode(locationCode);

        if (infield != null) {
            return new ModelAndView("redirect:/home?selectedMenuItem=infields");
        }

        infield = new Infield();
        infield.setTenant(landLord);
        infield.setLocationCode(locationCode);
        infield.setAreaHa(areaHa);
        infield.setCounty(county);
        infield.setVillage(village);
        infield.setLastYear(lastYear);
        // useless
        infield.setLandLordFullName(landLord.getFirstName() + landLord.getLastName());

        Society society = (Society) request.getSession().getAttribute("society");
        infield.setSociety(society);

//        infield.setTwoYearsAgo(twoYearsAgo);
//        infield.setThreeYearsAgo(threeYearsAgo);
//        infield.setFourYearsAgo(fourYearsAgo);
//        infield.setFiveYearsAgo(fiveYearsAgo);

        if (twoYearsAgo == null || twoYearsAgo.equals("")) infield.setTwoYearsAgo(null);
        else infield.setTwoYearsAgo(twoYearsAgo);

        if (threeYearsAgo == null || threeYearsAgo.equals("")) infield.setThreeYearsAgo(null);
        else infield.setThreeYearsAgo(threeYearsAgo);

        if (fourYearsAgo == null || fourYearsAgo.equals("")) infield.setFourYearsAgo(null);
        else infield.setFourYearsAgo(fourYearsAgo);

        if (fiveYearsAgo == null || fiveYearsAgo.equals("")) infield.setFiveYearsAgo(null);
        else infield.setFiveYearsAgo(fiveYearsAgo);

        Long infieldId = infieldService.addInfield(infield);

        if (infieldId == -1l) {
            redirectAttributes.addAttribute("error", "Error on save. Please try again.");
            return new ModelAndView("redirect:/infield/add");
        }

        return new ModelAndView("redirect:/home?selectedMenuItem=infields");
    }

}
