package com.agrocom.controller;

import com.agrocom.helpers.AppUtil;
import com.agrocom.helpers.SignUpForm;
import com.agrocom.model.User;
import com.agrocom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class SignUpController {

    private Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView doRegister(
            @RequestParam(value = "first-name") String firstName,
            @RequestParam(value = "last-name") String lastName,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "confirm-password") String confirmedPassword,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        // todo add modelAttribute for signUpForm

        ModelAndView mv = new ModelAndView("redirect:/login");

        if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "emptyFields");
            return mv;
        }

        if (!AppUtil.isValidEmail(email)) {
            redirectAttributes.addFlashAttribute("error", "invalidEmail");
            return mv;
        }

        if (firstName.length() <= 3 || lastName.length() <= 3) {
            redirectAttributes.addFlashAttribute("error", "firstNameOrLastNameTooShort");
            return mv;
        }

        if (password.length() < 6) {
            redirectAttributes.addFlashAttribute("error", "invalidPassword");
            return mv;
        }

        if (!password.equals(confirmedPassword)) {
            redirectAttributes.addFlashAttribute("error", "passwordsDoNotMatch");
            return mv;
        }

        User userByEmail = userService.getUserByEmail(email);
        if (userByEmail != null) {
            redirectAttributes.addFlashAttribute("error", "emailAlreadyUsed");
            return mv;
        }

        // check if the exact first name and last name pair already exists
        User userByFirstAndLastName = userService.getUserByFirstAndLastName(firstName, lastName);
        if (userByFirstAndLastName != null) {
            redirectAttributes.addFlashAttribute("error", "nameAlreadyUsed");
            return mv;
        }

        // every engineer can create accounts for workers and tenants and , afterwards, they will log in
        // a generated token will be created and send to them via email
        // at first user they will be advised to change that password

        // create user
        User user = userService.createUserWithoutSaving(firstName, lastName, email, password);

        Boolean success = userService.sendSignUpEmail(user);
//      if success ...
        if (success) {
            Integer createdUserId = userService.addUser(user);

            if (createdUserId == -1) {
                logger.warn("User could not be created. Redirect to login error");
                redirectAttributes.addFlashAttribute("error", "errorCreatingUser");

                return mv;
            } else {
                logger.debug("User created (unconfirmed): " + user);
            }
        }

//        // create user and redirect to first page
        /*UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

//        Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
//
//        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);*/

        // account confirmation via email required before login
        return new ModelAndView("redirect:/firstPage");
    }
}
