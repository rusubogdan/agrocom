package com.agrocom.controller;

import com.agrocom.model.Role;
import com.agrocom.model.Society;
import com.agrocom.model.User;
import com.agrocom.model.UserSociety;
import com.agrocom.service.RoleService;
import com.agrocom.service.SocietyService;
import com.agrocom.service.UserService;
import com.agrocom.service.UserSocietyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/societies")
public class SocietyController {

    private Logger logger = LoggerFactory.getLogger(SocietyController.class);

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    SocietyService societyService;

    @Autowired
    UserSocietyService userSocietyService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView servePage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("societies");

        if (request.getSession().getAttribute("user") == null) {
            User loggedInUser = userService
                    .getUserByEmail(((org.springframework.security.core.userdetails.User)
                            SecurityContextHolder.getContext().getAuthentication()
                                    .getPrincipal()).getUsername());

            request.getSession().setAttribute("user", loggedInUser);
        }

        List<UserSociety> userSocieties = userSocietyService
                .getUserSocietyByUser((User) request.getSession().getAttribute("user"));

        List<Society> societies = userSocieties.stream()
                .map(UserSociety::getSociety).collect(Collectors.toList());

        mv.addObject("societies", societies);

        mv.addObject("selectedMenuItem", "general");

        return mv;
    }

    @RequestMapping(value = "/ajax/society/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map loadSociety(@PathVariable(value = "id") Long id,
                                    HttpServletRequest request) {
        Map response = new HashMap<>();

        Society society = societyService.getSociety(id, true);
        request.getSession().setAttribute("society", society);

        User user = (User) request.getSession().getAttribute("user");

        UserSociety userSociety = userSocietyService
                .getUserSocietyByUserAndSociety(user, society);
        request.getSession().setAttribute("userSociety", userSociety);

        response.put("redirectUrl", "/home");

        return response;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView serveAddSocietyPage() {
        return new ModelAndView("addSociety");
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public ModelAndView addSociety(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "phone") String phone,
                                   @RequestParam(value = "address") String address,
                                   HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("addSociety");
        Society society = societyService.getSocietyByName(name, true);

        if (society != null) {
            mv.addObject("error", "Name already exists");
            return mv;
        }

        society = new Society();
        society.setName(name);
        society.setPhone(phone);
        society.setAddress(address);
        User user = (User) request.getSession().getAttribute("user");
        society.setOwner(user);

        Long societyId = societyService.addSociety(society);

        if (societyId == -1l) {
            mv.addObject("error", "Error on adding");
            return mv;
        }

        // add admin role to society to authenticated user
        UserSociety userSociety = new UserSociety();
        userSociety.setUser(user);
        userSociety.setSociety(society);
        userSociety.setRole(roleService.getRole(Role.ROLE_ADMIN));

        userSocietyService.addUserSociety(userSociety);

        return new ModelAndView("redirect:/societies");
    }

}
