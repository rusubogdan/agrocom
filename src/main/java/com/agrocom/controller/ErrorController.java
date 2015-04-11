package com.agrocom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "")
public class ErrorController {

    @RequestMapping(value = "/505")
    public ModelAndView serve505ErrorPage () {
        return new ModelAndView("505");
    }

    @RequestMapping(value = "/404")
    public ModelAndView serve404ErrorPage () {
        return new ModelAndView("404");
    }

}
