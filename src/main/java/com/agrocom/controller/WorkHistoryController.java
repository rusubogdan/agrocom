package com.agrocom.controller;

import com.agrocom.model.Infield;
import com.agrocom.model.WorkHistory;
import com.agrocom.service.InfieldService;
import com.agrocom.service.UserService;
import com.agrocom.service.WorkHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/workHistory")
public class WorkHistoryController {

    @Autowired
    private WorkHistoryService workHistoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private InfieldService infieldService;

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView serveViewPage(@PathVariable Long id,
                                      HttpServletRequest request,
                                      RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("viewActivity");
        WorkHistory activity = workHistoryService.getWorkHistory(id, true);

        if (activity == null) {
            redirectAttributes.addAttribute("selectedMenuItem", "activities");
            return new ModelAndView("redirect:/home?selectedMenuItem=activities");
        }

        mv.addObject("activity", activity);

        return mv;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView serverEditPage(@PathVariable Long id,
                                       HttpServletRequest request,
                                       RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("editActivity");
        WorkHistory activity = workHistoryService.getWorkHistory(id, true);

        if (activity == null) {
            redirectAttributes.addAttribute("selectedMenuItem", "activities");
            return new ModelAndView("redirect:/home?selectedMenuItem=activities");
        }



        return mv;
    }

}
