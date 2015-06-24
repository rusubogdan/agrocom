package com.agrocom.controller;

import com.agrocom.helpers.MessageServer;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginFormAfterRequest(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "forbidden", required = false) String forbidden,
            @RequestParam(value = "register-successful", required = false) String registerSuccessful,
            Model model,
            HttpServletRequest request) {
        // todo check here for admin or moderator or use in JSP sec tag!!!

        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/societies");
        }

        ModelAndView mv = new ModelAndView("login");

        String constraintError = (String) model.asMap().get("error");

        if (constraintError != null) {
            mv.addObject("showRegistrationForm", true);
            mv.addObject("error", MessageServer.serveErrorMessage(constraintError));
            return mv;
        }

        mv.addObject("showRegistrationForm", false);

        if (error != null) {
            mv.addObject("error", "Invalid username or password!");
        }

        if (logout != null) {
            mv.addObject("msg", "You've been logged out successfully.");
        }

        if (forbidden != null) {
            mv.addObject("notAllowed", "Please login first");
        }

        if (registerSuccessful != null) {
            mv.addObject("registerSuccessful", "Register successfully");
        }

        String success = (String) model.asMap().get("success");

        if(success != null) {
            mv.addObject("success", "Please confirm email!");
        }

        return mv;
    }
}