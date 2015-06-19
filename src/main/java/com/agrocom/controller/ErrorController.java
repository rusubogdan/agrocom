package com.agrocom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "")
public class ErrorController {

    @RequestMapping(value = "/500")
    public ModelAndView serve500ErrorPage () {
        return new ModelAndView("500");
    }

    @RequestMapping(value = "/404")
    public ModelAndView serve404ErrorPage () {
        return new ModelAndView("404");
    }

}
