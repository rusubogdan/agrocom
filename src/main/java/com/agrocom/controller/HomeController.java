package com.agrocom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class HomeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView homePage() {
        // some introduction of the platform
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("content", "content");
        return mv;
    }
}
