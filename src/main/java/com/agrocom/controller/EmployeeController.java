package com.agrocom.controller;

import com.agrocom.helpers.MinifyUtil;
import com.agrocom.model.Role;
import com.agrocom.model.Society;
import com.agrocom.model.User;
import com.agrocom.model.UserSociety;
import com.agrocom.service.RoleService;
import com.agrocom.service.UserService;
import com.agrocom.service.UserSocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserSocietyService userSocietyService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addEmployeePage(HttpServletRequest request,
                                        RedirectAttributes attributes,
                                        @RequestParam(value = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("addEmployeePage");

        if (error != null)
            mv.addObject("error", error);

        attributes.asMap().clear();
        return mv;
    }

    // an employee from existing users
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public ModelAndView addEmployee(@RequestParam(value = "emailOrPIN") String emailOrPIN,
                                    HttpServletRequest request,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("redirect:/employee/add");

        Society society = (Society) request.getSession().getAttribute("society");

        User user = userService.getUserByEmail(emailOrPIN);

        if (user == null) {
            user = userService.getUserByPin(emailOrPIN);

            if (user == null) {
                redirectAttributes.addAttribute("error",
                        "No employee found with the given email or PIN");
                return mv;
            }
        }

        UserSociety userSociety = userSocietyService.getUserSocietyByUserAndSociety(user, society);

        if (userSociety != null) {
            redirectAttributes.addAttribute("error",
                    "User is already an employee");
            return mv;
        }

        userSociety = new UserSociety();
        userSociety.setUser(user);
        userSociety.setSociety(society);
        userSociety.setRole(roleService.getRole(Role.ROLE_USER));

        userSocietyService.addUserSociety(userSociety);

        // todo notification email

        redirectAttributes.addAttribute("selectedMenuItem", "employees");
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView serverViewPage (@PathVariable(value = "id") Long id,
                                        HttpServletRequest request) {
        User employee = userService.getUser(id, true);
        ModelAndView mv = new ModelAndView("viewEmployee");

        if (employee == null) {
            return new ModelAndView("redirect:/home");
        }

        employee = MinifyUtil.minifyUser(employee);
        mv.addObject("employee", employee);

        return mv;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView serveEditPage (@PathVariable(value = "id") Long id,
                                       HttpServletRequest request) {
        User employee = userService.getUser(id, true);
        ModelAndView mv = new ModelAndView("editEmployee");

        if (employee == null) {
            return new ModelAndView("redirect:/employee/view/" + id);
        }

        Society society = (Society) request.getSession().getAttribute("society");



        UserSociety userSociety = userSocietyService.getUserSocietyByUserAndSociety(employee, society);
        Long role = userSociety.getRole().getRoleId();
        mv.addObject("role", role);

        employee = MinifyUtil.minifyUser(employee);
        mv.addObject("employee", employee);

        return mv;
    }

    // todo learn how to user @Model
    @RequestMapping(value = "/edit.do", method = RequestMethod.POST)
    public ModelAndView editEmployee(@RequestParam String firstName,
                                     @RequestParam String lastName,
                                     @RequestParam String PIN,
                                     @RequestParam String email,
                                     @RequestParam(required = false) String phone,
                                     @RequestParam(required = false) String mobile,
                                     @RequestParam Long role,
                                     @RequestParam Long userId,
                                     RedirectAttributes redirectAttributes,
                                     HttpServletRequest request) {

        User user = userService.getUser(userId, true);

        if (user == null) {
            return new ModelAndView("redirect:/home");
        }

        User testUser = userService.getUserByPin(PIN);
        if (testUser != null && !testUser.equals(user)) {
            return new ModelAndView("redirect:/employee/edit/" + userId);
        }

        testUser = userService.getUserByEmail(email);
        if (testUser != null && !testUser.equals(user)) {
            return new ModelAndView("redirect:/employee/edit/" + userId);
        }

        // todo test this shit
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPIN(PIN);
        user.setEmail(email);
        user.setPhone(phone.equals("") ? null : phone);
        user.setMobile(mobile.equals("") ? null : mobile);

        userService.updateUser(user);

        Society society = (Society) request.getSession().getAttribute("society");

        UserSociety userSociety = userSocietyService.getUserSocietyByUserAndSociety(user, society);

        Role roleInSociety;
        if (role == 1l) {
            roleInSociety = roleService.getRole(Role.ROLE_ADMIN);
        } else {
            roleInSociety = roleService.getRole(Role.ROLE_USER);
        }
        userSociety.setRole(roleInSociety);

        userSocietyService.addUserSociety(userSociety);

//        redirectAttributes.addAttribute("selectedMenuItem", "employees");
        return new ModelAndView("redirect:/employee/view/" + userId);
    }
}
