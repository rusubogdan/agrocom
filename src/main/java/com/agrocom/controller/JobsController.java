package com.agrocom.controller;

import com.agrocom.helpers.MinifyUtil;
import com.agrocom.model.*;
import com.agrocom.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/job")
public class JobsController {


    @Autowired
    private WorkHistoryService workHistoryService;

    @Autowired
    private UserSocietyService userSocietyService;

    @Autowired
    private UserService userService;

    @Autowired
    private InfieldService infieldService;

    @Autowired
    private MachineryService machineryService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView serveViewPage(@PathVariable Long id) {
        return new ModelAndView("redirect:/job/edit/" + id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView serverEditPage(@PathVariable Long id,
                                       HttpServletRequest request,
                                       RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("editJob");
        WorkHistory activity = workHistoryService.getWorkHistory(id, true);

        if (activity == null) {
            redirectAttributes.addAttribute("selectedMenuItem", "jobs");
            return new ModelAndView("redirect:/home?selectedMenuItem=jobs");
        }

        Society society = (Society) request.getSession().getAttribute("society");

        //user
        List<User> employees = new ArrayList<>();

        Role role = roleService.getRole(Role.ROLE_USER);
        List<UserSociety> employeesOfSociety = userSocietyService
                .getUserSocietyBySociety(society, true, role);
        employees.addAll(employeesOfSociety.stream().map(UserSociety::getUser)
                .collect(Collectors.toList()));

        //infield
        List<Infield> infields = new ArrayList<>(society.getInfields());

        //machinery - retrieves all machineries !!! todo change logic
        List<Machinery> machineries = machineryService.getMachineriesBySociety(society);
//        List<Machinery> machineries = new ArrayList<>(society.get);
        machineries = MinifyUtil.minifyMachineries(machineries);
        mv.addObject("machineries", machineries);
        mv.addObject("machineryId", activity.getMachinery().getMachineryId());

        employees = MinifyUtil.minifyUsers(employees);
        mv.addObject("employees", employees);
        mv.addObject("employeeId", activity.getWorker().getUserId());

        infields = MinifyUtil.minifyInfields(infields);
        mv.addObject("infields", infields);
        mv.addObject("infieldId", activity.getInfield().getInfieldId());

        List<String> workTypes = new ArrayList<>();
        workTypes.add("PLOWING");
        workTypes.add("SOWING");
        workTypes.add("HARVEST");

        mv.addObject("workTypes", workTypes);

        mv.addObject("activity", activity);

        return mv;
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam Long workHistoryId,
                               @RequestParam Long employeeId,
                               @RequestParam Long infieldId,
                               @RequestParam Long machineryId,
                               @RequestParam String workTypeId,
                               @RequestParam String description,
                               @RequestParam Integer duration,
                               @RequestParam String status,
                               HttpServletRequest request,
                               RedirectAttributes redirectAttributes) {

        WorkHistory activity = workHistoryService.getWorkHistory(workHistoryId, true);

        if (activity == null) {
            return new ModelAndView("redirect:/home?selectedMenuItem=jobs");
        }

        User user = userService.getUser(employeeId, true);
        Machinery machinery = machineryService.getMachinery(machineryId);
        Infield infield = infieldService.getInfield(infieldId, true);

        WorkType workTypeObject = null;

        switch (workTypeId) {
            case "HARVEST": {
                workTypeObject = WorkType.HARVEST;
                break;
            }
            case "SOWING": {
                workTypeObject = WorkType.SOWING;
                break;
            }
            case "PLOWING": {
                workTypeObject = WorkType.PLOWING;
                break;
            }
        }

        activity.setWorker(user);
        activity.setInfield(infield);
        activity.setMachinery(machinery);
        activity.setWorkType(workTypeObject);
        activity.setDescription(description);
        activity.setDuration(duration);
        activity.setStatus(status);

        workHistoryService.updateWorkHistory(activity);

        return new ModelAndView("redirect:/home?selectedMenuItem=jobs");
    }


}
