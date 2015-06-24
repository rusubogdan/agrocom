package com.agrocom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/tenant")
public class TenantController {


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView serveAddPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("addTenantPage");

        return mv;
    }
}
