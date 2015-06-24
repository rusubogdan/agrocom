package com.agrocom.controller;

import com.agrocom.helpers.MinifyUtil;
import com.agrocom.model.*;
import com.agrocom.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/home", method = RequestMethod.GET)
public class HomeController {

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @Autowired
    SocietyService societyService;

    @Autowired
    InfieldService infieldService;

    @Autowired
    UserSocietyService userSocietyService;

    @Autowired
    WorkHistoryService workHistoryService;

    @Autowired
    RoleService roleService;

    @Autowired
    PaymentService paymentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request,
                             @RequestParam(value = "selectedMenuItem", required = false)
                                String selectedMenuItem) {
        ModelAndView mv = new ModelAndView("home");

        if (request.getSession().getAttribute("user") == null) {
            User loggedInUser = userService
                    .getUserByEmail(((org.springframework.security.core.userdetails.User)
                            SecurityContextHolder.getContext().getAuthentication()
                                    .getPrincipal()).getUsername());
            request.getSession().setAttribute("user", loggedInUser);
        }

        if (request.getSession().getAttribute("society") == null) {
            return new ModelAndView("redirect:/societies");
        }

        // add variables for header
        mv.addObject("loggedIn", true);
        mv.addObject("isTenant", null);
        mv.addObject("isEmployee", null);
        mv.addObject("isEngineer", null);
        mv.addObject("companyName", "Cecapa");
        mv.addObject("fullName", "Cecapa");

        if (selectedMenuItem == null)
            mv.addObject("selectedMenuItem", "general");
        else
            mv.addObject("selectedMenuItem", selectedMenuItem);

        return mv;
    }

    @RequestMapping(value = "/ajax/getActivities", method = RequestMethod.GET)
    @ResponseBody
    public List<WorkHistoryMin> getActivities(HttpServletRequest request) {
        Society society = (Society) request.getSession().getAttribute("society");

        List<WorkHistory> activities = workHistoryService.getWorkHistoryBySociety(society);
        List<WorkHistoryMin> activitiesMin = new ArrayList<>();
        WorkHistoryMin activityMin = new WorkHistoryMin();

        for (WorkHistory activity : activities) {
            activityMin = new WorkHistoryMin();
            activityMin.workHistoryId = activity.getWorkHistoryId();
            activityMin.fullName = activity.getWorker().getFirstName() + " "
                    + activity.getWorker().getLastName();
            activityMin.locationCode = activity.getInfield().getLocationCode();
            activityMin.machineryName = activity.getMachinery().getMachineryName();
            activityMin.description = activity.getDescription();
            activityMin.duration = activity.getDuration();
            activityMin.status = activity.getStatus();
            activityMin.workType = activity.getWorkType().toString();
            activityMin.date = activity.getDate();

            activitiesMin.add(activityMin);
        }

        return activitiesMin;
    }

    @RequestMapping(value = "/ajax/getJobs", method = RequestMethod.GET)
    @ResponseBody
    public List<WorkHistoryMin> getMyActivities(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Society society = (Society) request.getSession().getAttribute("society");

        List<WorkHistory> activities =
                workHistoryService.getWorkHistoryByUserAndSociety(user, society);

        List<WorkHistoryMin> activitiesMin = new ArrayList<>();
        WorkHistoryMin activityMin = new WorkHistoryMin();

        for (WorkHistory activity : activities) {
            activityMin = new WorkHistoryMin();
            activityMin.workHistoryId = activity.getWorkHistoryId();
            activityMin.fullName = activity.getWorker().getFirstName() + " "
                    + activity.getWorker().getLastName();
            activityMin.locationCode = activity.getInfield().getLocationCode();
            activityMin.machineryName = activity.getMachinery().getMachineryName();
            activityMin.description = activity.getDescription();
            activityMin.duration = activity.getDuration();
            activityMin.status = activity.getStatus();
            activityMin.workType = activity.getWorkType().toString();
            activityMin.date = activity.getDate();

            activitiesMin.add(activityMin);
        }

        return activitiesMin;
    }


    @RequestMapping(value = "/ajax/getEmployees", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getEmployees(HttpServletRequest request) {
        Society society = (Society) request.getSession().getAttribute("society");
        User loggedInUser = (User) request.getSession().getAttribute("user");

        Role role = roleService.getRole(Role.ROLE_USER);
        List<UserSociety> userSocieties = userSocietyService
                .getUserSocietyBySociety(society, true, role);

        List<User> employees = userSocieties.stream()
                .map(UserSociety::getUser).collect(Collectors.toList());

        List<User> emp = new ArrayList<>(employees);
        emp.remove(loggedInUser);

        MinifyUtil.minifyUsers(emp);

        return emp;
    }

    @RequestMapping(value = "/ajax/getPayments", method = RequestMethod.GET)
    @ResponseBody
    public List<PaymentMin> getPayments(HttpServletRequest request) {
        List<PaymentMin> paymentMins = new ArrayList<>();
        Society society = (Society) request.getSession().getAttribute("society");
        List<Payment> payments = paymentService.getPaymentBySociety(society);

        PaymentMin paymentMin = new PaymentMin();
        for(Payment payment : payments) {
            paymentMin = new PaymentMin();
            paymentMin.paymentId = payment.getPaymentId();
            paymentMin.societyId = payment.getSociety().getSocietyId();
            paymentMin.tenantName = payment.getTenant().getFirstName() + " "
                    + payment.getTenant().getLastName();
            paymentMin.paymentType = payment.getPaymentType();
            paymentMin.paymentValue = payment.getPaymentValue();
            paymentMin.date = payment.getPaymentDate();

            paymentMins.add(paymentMin);
        }

        return paymentMins;
    }


    @RequestMapping(value = "/ajax/getTenants", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getTenants(HttpServletRequest request) {
        List<User> list = new ArrayList<>();

        Society society = (Society) request.getSession().getAttribute("society");

        Role role = roleService.getRole(Role.ROLE_MODERATOR);
        List<UserSociety> userSocieties = userSocietyService
                .getUserSocietyBySociety(society, true, role);

        for(UserSociety userSociety : userSocieties) {
            list.add(userSociety.getUser());
        }

        List<User> users = new ArrayList<>(list);
        users = MinifyUtil.minifyUsers(users);

        return users;
    }

    @RequestMapping(value = "/ajax/getInfields", method = RequestMethod.GET)
    @ResponseBody
    public List<Infield> getInfields(HttpServletRequest request) {
        Society society = new Society((Society) request.getSession().getAttribute("society"));

        society = societyService.getSociety(society.getSocietyId(), true);

        List<Infield> societyInfields = new ArrayList<>(society.getInfields());

        societyInfields.stream().filter(infield -> infield.getTenant() != null).
                forEach(infield -> infield.setLandLordFullName(infield.getTenant().getFirstName() + " "
                + infield.getTenant().getLastName()));

        societyInfields = MinifyUtil.minifyInfields(societyInfields);

        return societyInfields;
    }


    @RequestMapping(value = "/ajax/getGarages", method = RequestMethod.GET)
    @ResponseBody
    public List<GarageMin> getGarages() {
        List<GarageMin> list = new ArrayList<GarageMin>();

        GarageMin garage;

        for (int i = 0; i < 8; i++) {
            garage = new GarageMin();
            garage.Id = i + 5;
            garage.Name = "garage" + i;
            garage.Address = "address" + i;
            garage.Capacity = 100 + i;
            list.add(garage);
        }

        return list;
    }

    @RequestMapping(value = "/ajax/getSocieties", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getSocieties() {
        List<String> societies = new ArrayList<>();

        for(int i=0; i<=5; i++) {
            societies.add("SC Agro SRL" + i);
        }

        return societies;
    }

    @RequestMapping(value = "/ajax/getRole", method = RequestMethod.GET)
    @ResponseBody
    public Map getRole(HttpServletRequest request) {
        Map map = new HashMap<>();

        UserSociety userSociety = (UserSociety) request.getSession().getAttribute("userSociety");
        Role role = userSociety.getRole();

        map.put("role", role.getRoleId());

        return map;
    }

    @RequestMapping(value = "/ajax/getGeneralInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map getGeneralInfo(HttpServletRequest request) {
        Map map = new HashMap<>();

        Society society = (Society) request.getSession().getAttribute("society");

        List<Garage> garages = new ArrayList<Garage>(society.getGarages());
        int capacity = 0;
        for(Garage garage : garages) {
            capacity += garage.getCapacity();
        }

        map.put("garagesCapacity", capacity);

        List<Infield> infields = new ArrayList<>(society.getInfields());
        double totalArea = 0;
        for(Infield infield : infields) {
            totalArea += infield.getAreaHa();
        }

        Role role = roleService.getRole(Role.ROLE_USER);
        List<UserSociety> employees = userSocietyService
                .getUserSocietyBySociety(society, true, role);

        BigDecimal bd = new BigDecimal(totalArea).setScale(4, BigDecimal.ROUND_HALF_DOWN);

        map.put("totalArea", bd);
        map.put("address", society.getAddress());
        map.put("societyName", society.getName());
        map.put("owner", society.getOwner().getFirstName() + " " + society.getOwner().getLastName());
        map.put("employeesNumber", employees.size());

        return map;
    }

    public class GarageMin {
        public Integer Id;
        public String Name;
        public Integer Capacity;
        public String Address;
    }

    public class WorkHistoryMin implements Serializable {
        public Long workHistoryId;
        public String fullName;
        public String locationCode;
        public String machineryName;
        public String workType;
        public Timestamp date;
        public String description;
        public Integer duration;
        public String status;
    }

    public class PaymentMin implements Serializable {
        public Long paymentId;
        public Long societyId;
        public String tenantName;
        public String paymentType;
        public String paymentValue;
        public Timestamp date;
    }


}
