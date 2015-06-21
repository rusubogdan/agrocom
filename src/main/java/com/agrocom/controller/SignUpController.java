package com.agrocom.controller;

import com.agrocom.helpers.AppUtil;
import com.agrocom.model.User;
import com.agrocom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
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
            @RequestParam(value = "pin") String pin,
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

        // check if the exact pin already exists
        User userByPin = userService.getUserByPin(pin);
        if (userByPin != null) {
            redirectAttributes.addFlashAttribute("error", "pinAlreadyUsed");
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
        User user = userService.createUserWithoutSaving(firstName, lastName, email, pin, password);

        Boolean success = userService.sendSignUpEmail(user);
        if (success) {
            Long createdUserId = userService.addUser(user);

            if (createdUserId == -1l) {
                logger.warn("User could not be created. Redirect to login error");
                redirectAttributes.addFlashAttribute("error", "errorCreatingUser");

                return mv;
            } else {
                logger.debug("User created (unconfirmed): " + user);
            }
        }
        // account confirmation via email required before login
        redirectAttributes.addFlashAttribute("success", "confirmEmail");
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/confirm/{token}", method = RequestMethod.GET)
    public ModelAndView confirm(@PathVariable(value = "token") String token,
                                RedirectAttributes redirectAttributes,
                                HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("redirect:/login");
        User userByToken = userService.getUserByToken(token);

        if (userByToken == null) {
            redirectAttributes.addAttribute("error", "invalidToken");
            return mv;
        }

        // confirm user account
        userByToken.setIsConfirmed(true);
        userService.updateUser(userByToken);

        /*UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(
                        userByToken.getEmail(),
                        *//*userByToken.getPassword()*//*"123123",
                        getAuthorities(userByToken.getRole().getRoleId()));*/

        UserDetails user =
                new org.springframework.security.core.userdetails.User
                        (userByToken.getEmail(),
                        userByToken.getPassword(),
                        getAuthorities(userByToken.getRole().getRoleId()));

//        Authenticate the user
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
                user.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
//
//        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return new ModelAndView("redirect:/societies");
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Long role) {
        return getGrantedAuthorities(getRoles(role));
    }

    public List<String> getRoles(Long role) {

        List<String> roles = new ArrayList<String>();

        if (role == 1) {
            roles.add("ROLE_MODERATOR");
            roles.add("ROLE_ADMIN");
            roles.add("ROLE_USER");
        } else if (role == 2) {
            roles.add("ROLE_MODERATOR");
            roles.add("ROLE_USER");
        } else if (role == 3) {
            roles.add("ROLE_USER");
        } else if (role == 4) {
            roles.add("ROLE_RESTRICTED");
        }

        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
