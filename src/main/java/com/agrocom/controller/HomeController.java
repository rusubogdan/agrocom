package com.agrocom.controller;

import com.agrocom.helpers.MinifyUtil;
import com.agrocom.model.*;
import com.agrocom.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/home", method = RequestMethod.GET)
public class HomeController {

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @Autowired
    SocietyService societyService;

    @Autowired
    InfieldService infieldService;

    @Autowired
    UserSocietyService userSocietyService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request,
                             @RequestParam(value = "selectedMenuItem", required = false)
                                String selectedMenuItem) {
        ModelAndView mv = new ModelAndView("home");

        if (request.getSession().getAttribute("user") == null) {
            User loggedInUser = userService
                    .getUserByEmail(((org.springframework.security.core.userdetails.User)
                            SecurityContextHolder.getContext().getAuthentication()
                                    .getPrincipal()).getUsername());
            request.getSession().setAttribute("user", loggedInUser);
        }

        if (request.getSession().getAttribute("society") == null) {
            return new ModelAndView("redirect:/societies");
        }

        // add variables for header
        mv.addObject("loggedIn", true);
        mv.addObject("isTenant", null);
        mv.addObject("isEmployee", null);
        mv.addObject("isEngineer", null);
        mv.addObject("companyName", "Cecapa");
        mv.addObject("fullName", "Cecapa");

        if (selectedMenuItem == null)
            mv.addObject("selectedMenuItem", "general");
        else
            mv.addObject("selectedMenuItem", selectedMenuItem);

        return mv;
    }

    @RequestMapping(value = "/ajax/activities/{id}", method = RequestMethod.GET)
    @ResponseBody
    public WorkHistory getActivity(@PathVariable(value = "id") String idStr) {
        Integer id = Integer.parseInt(idStr);



        return null;
    }

    @RequestMapping(value = "/ajax/tenants/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getTenant(@PathVariable(value = "id") String idStr) {
        Integer id = Integer.parseInt(idStr);

        User user = new User();
        user.setFirstName("bla");
        user.setEmail("bla@gail.com");
        user.setPIN("1931193939234234");

        return user;
    }

    @RequestMapping(value = "/ajax/garages/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Garage getGarages(@PathVariable(value = "id") String idStr) {
        Integer id = Integer.parseInt(idStr);

        Garage garage = new Garage();
        garage.setName("myGarage");

        return garage;
    }

    @RequestMapping(value = "/ajax/getActivities", method = RequestMethod.GET)
    @ResponseBody
    public List<WorkHistory> getActivities(HttpServletRequest request) {
        Society society = (Society) request.getSession().getAttribute("society");



//        List<WorkHistory> list = new ArrayList<>();



//                workHistory.Date = new SimpleDateFormat("MM/dd/yyyy")
//                        .format(new SimpleDateFormat("MM/dd/yyyy").parse("07/" + i + "/2015"));

        return null;
    }


    @RequestMapping(value = "/ajax/getEmployees", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getEmployees(HttpServletRequest request) {
        Society society = (Society) request.getSession().getAttribute("society");
        User loggedInUser = (User) request.getSession().getAttribute("user");

        List<UserSociety> userSocieties = userSocietyService.getUserSocietyBySociety(society);

        List<User> employees = userSocieties.stream()
                .map(UserSociety::getUser).collect(Collectors.toList());

        List<User> emp = new ArrayList<>(employees);
        emp.remove(loggedInUser);

        MinifyUtil.minifyUsers(emp);

        return emp;
    }

    @RequestMapping(value = "/ajax/getTenants", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getTenants() {
        List<User> list = new ArrayList<>();

        return list;
    }

    @RequestMapping(value = "/ajax/getInfields", method = RequestMethod.GET)
    @ResponseBody
    public List<Infield> getInfields(HttpServletRequest request) {
        Society society = new Society((Society) request.getSession().getAttribute("society"));

        society = societyService.getSociety(society.getSocietyId(), true);

        List<Infield> societyInfields = new ArrayList<>(society.getInfields());

        societyInfields.stream().filter(infield -> infield.getTenant() != null).
                forEach(infield -> infield.setLandLordFullName(infield.getTenant().getFirstName() + " "
                + infield.getTenant().getLastName()));

        societyInfields = MinifyUtil.minifyInfields(societyInfields);

        return societyInfields;
    }


    @RequestMapping(value = "/ajax/getGarages", method = RequestMethod.GET)
    @ResponseBody
    public List<GarageMin> getGarages() {
        List<GarageMin> list = new ArrayList<GarageMin>();

        GarageMin garage;

        for (int i = 0; i < 8; i++) {
            garage = new GarageMin();
            garage.Id = i + 5;
            garage.Name = "garage" + i;
            garage.Address = "address" + i;
            garage.Capacity = 100 + i;
            list.add(garage);
        }

        return list;
    }

    @RequestMapping(value = "/ajax/getSocieties", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getSocieties() {
        List<String> societies = new ArrayList<>();

        for(int i=0; i<=5; i++) {
            societies.add("SC Agro SRL" + i);
        }

        return societies;
    }

    public class GarageMin {
        public Integer Id;
        public String Name;
        public Integer Capacity;
        public String Address;
    }
}
