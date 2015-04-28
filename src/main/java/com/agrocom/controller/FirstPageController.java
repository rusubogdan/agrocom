package com.agrocom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class FirstPageController {

    @RequestMapping(value = "")
    public ModelAndView firstPage() {
        ModelAndView mv = new ModelAndView("firstPage");


        return mv;
    }
}
