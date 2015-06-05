package com.agrocom.controller;

import com.agrocom.model.Garage;
import com.agrocom.model.Infield;
import com.agrocom.model.User;
import com.agrocom.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/home", method = RequestMethod.GET)
@PropertySource("classpath:application.properties")
public class HomeController {

    private static final String sendGridUsername = "mail.sendGrid.username";
    private static final String sendGridPassword = "mail.sendGrid.password";

    @Resource
    private Environment env;

    @Autowired
    MailService mailService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");

        // add variables for header
        mv.addObject("loggedIn", true);
        mv.addObject("isTenant", null);
        mv.addObject("isEmployee", null);
        mv.addObject("isEngineer", null);
        mv.addObject("companyName", "Cecapa");
        mv.addObject("fullName", "Cecapa");

        return mv;
    }

    @RequestMapping(value = "/ajax/activities/{id}", method = RequestMethod.GET)
    @ResponseBody
    public WorkHistoryMin getActivity(@PathVariable(value = "id") String idStr) {
        Integer id = Integer.parseInt(idStr);

//        User user = new User();
//        user.setFirstName("bla");
//        user.setEmail("bla@gail.com");
//        user.setCNP("1931193939234234");

        WorkHistoryMin work = new WorkHistoryMin();
        work.Id = 12;

        return work;
    }


    @RequestMapping(value = "/ajax/employees/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getEmployee(@PathVariable(value = "id") String idStr) {
        Integer id = Integer.parseInt(idStr);

        User user = new User();
        user.setFirstName("bla");
        user.setEmail("bla@gail.com");
        user.setCNP("1931193939234234");

        return user;
    }

    @RequestMapping(value = "/ajax/tenants/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getTenant(@PathVariable(value = "id") String idStr) {
        Integer id = Integer.parseInt(idStr);

        User user = new User();
        user.setFirstName("bla");
        user.setEmail("bla@gail.com");
        user.setCNP("1931193939234234");

        return user;
    }

    @RequestMapping(value = "/ajax/infields/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Infield getInfield(@PathVariable(value = "id") String idStr) {
        Integer id = Integer.parseInt(idStr);

        Infield infield = new Infield();
        infield.setCounty("Calarasi");
        infield.setLocationCode("43233FSAD");
        infield.setLastYear("corn");

        return infield;
    }

    @RequestMapping(value = "/ajax/garages/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Garage getGarages(@PathVariable(value = "id") String idStr) {
        Integer id = Integer.parseInt(idStr);

        Garage garage = new Garage();
        garage.setName("myGarage");

        return garage;
    }

    @RequestMapping(value = "/ajax/getActivities", method = RequestMethod.GET)
    @ResponseBody
    public List<WorkHistoryMin> getActivities() {
        List<WorkHistoryMin> list = new ArrayList<>();

        WorkHistoryMin workHistory;

        for (int i = 11; i <19; i++) {
            workHistory = new WorkHistoryMin();
            workHistory.Id = i + 5;
            workHistory.Worker = "worker" + i;
            workHistory.Infield = "infield" + i;
            try {
                workHistory.Date = new SimpleDateFormat("MM/dd/yyyy")
                        .format(new SimpleDateFormat("MM/dd/yyyy").parse("07/" + i + "/2015"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            workHistory.Machinery = "tractor";
            workHistory.WorkType = "arat";
            list.add(workHistory);
        }

        return list;
    }


    @RequestMapping(value = "/ajax/getEmployees", method = RequestMethod.GET)
    @ResponseBody
    public List<UserMin> getEmployees() {
        List<UserMin> list = new ArrayList<>();

        UserMin user;

        List<String> fullnames = new ArrayList<>();
        fullnames.add("Popescu Ion");
        fullnames.add("Ionescu Constantin");
        fullnames.add("Petre Mircea");

        List<String> cnps = new ArrayList<>();
        cnps.add("1670204343467");
        cnps.add("1700112311459");
        cnps.add("1590511204347");

        List<String> emails = new ArrayList<>();
        emails.add("ipopescu@gmail.com");
        emails.add("cionescu@gmail.com");
        emails.add("mpetre@gmail.com");

        List<String> mobiles = new ArrayList<>();
        mobiles.add("0756136282");
        mobiles.add("0751111485");
        mobiles.add("0721957893");

        for (int i = 0; i < 3; i++) {
            user = new UserMin();
            user.Id = i + 1;
            user.FullName = fullnames.get(i);
            user.CNP = cnps.get(i);
            user.Email = emails.get(i);
            user.Mobile = mobiles.get(i);
            list.add(user);
        }

        return list;
    }

    @RequestMapping(value = "/ajax/getTenants", method = RequestMethod.GET)
    @ResponseBody
    public List<UserMin> getTenants() {
        List<UserMin> list = new ArrayList<>();
        UserMin user;

        for (int i = 0; i < 3; i++) {
            user = new UserMin();
            user.Id = i + 5;
            user.FullName = "tenant" + i;
            user.CNP = "193110302030234";
            user.Email = "tenant" + i + "@gmail.com";
            user.Mobile = "0743281908";
            list.add(user);
        }

        return list;
    }

    @RequestMapping(value = "/ajax/getInfields", method = RequestMethod.GET)
    @ResponseBody
    public List<InfieldMin> getInfields() {
        List<InfieldMin> list = new ArrayList<>();

        InfieldMin infield;

        for (int i = 0; i < 11; i++) {
            infield = new InfieldMin();
            infield.Id = i + 5;
            infield.Code = "abcv" + i;
            infield.County = "Calarasi" + i;
            infield.Village = "ComCalarsi" + i;
            infield.LastYear = "corn";
            infield.TwoYearsAgo = "sunflower";
            infield.ThreeYearsAgo = "soy";
            infield.FourYearsAgo = "corn";
            infield.FiveYearsAgo = "wheat";

            list.add(infield);
        }

        return list;
    }


    @RequestMapping(value = "/ajax/getGarages", method = RequestMethod.GET)
    @ResponseBody
    public List<GarageMin> getGarages() {
        List<GarageMin> list = new ArrayList<>();

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


    @RequestMapping(value = "/ajax/sendPostMessage", method = RequestMethod.POST)
    @ResponseBody
    public List<String> testPost() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

        return list;
    }

    public class UserMin {
        public Integer Id;
        public String FullName;
        public String CNP;
        public String Mobile;
        public String Email;
    }

    public class InfieldMin {
        public Integer Id;
        public String Code;
        public String County;
        public String Village;
        public String LastYear;
        public String TwoYearsAgo;
        public String ThreeYearsAgo;
        public String FourYearsAgo;
        public String FiveYearsAgo;
    }

    public class GarageMin {
        public Integer Id;
        public String Name;
        public Integer Capacity;
        public String Address;
    }

    public class WorkHistoryMin {
        public Integer Id;
        public String Worker;
        public String Infield;
        public String Machinery;
        public String WorkType;
        public String Date;
    }
}
