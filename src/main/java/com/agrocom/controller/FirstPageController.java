package com.agrocom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class FirstPageController {


    /*
    * Here will be the public section where anybody can see the new announcements
    * */

    @RequestMapping(value = "")
    public ModelAndView firstPage() {
        ModelAndView mv = new ModelAndView("firstPage");

        // add variables for header
        mv.addObject("loggedIn", false);
        mv.addObject("isTenant", null);
        mv.addObject("isEmployee", null);
        mv.addObject("isEngineer", null);
        mv.addObject("companyName", "Cecapa");
        mv.addObject("fullName", "Cecapa");


        // here will be the staff before logging, some presentation




//        return mv;
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public ModelAndView serverPublicPage() {
        return new ModelAndView("publicPage");
    }


}
