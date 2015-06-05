package com.agrocom.controller;

import com.agrocom.helpers.Announcement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "/public")
public class PublicController {

    @RequestMapping(value = "/society", method = RequestMethod.GET)
    public SocietyMin getSocietyAnnouncements() {

        return null;
    }

    @RequestMapping(value = "/ajax/getSocieties", method = RequestMethod.GET)
    @ResponseBody
    public List<SocietyMin> getSocieties() {
        List<SocietyMin> societies = new ArrayList<>();
        SocietyMin society;

        for (int i = 0; i < 10; i++) {
            society = new SocietyMin();
            society.Id = i + 3;
            society.Name = "society" + i;
            societies.add(society);
        }

        return societies;
    }

    @RequestMapping(value = "/society/{id}/announcements", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getAnnouncements(@PathVariable(value = "id") String id) {
        ModelAndView mv = new ModelAndView("announcements");
        List<Announcement> announcements = new ArrayList<>();

        Integer societyId = Integer.parseInt(id);

        Announcement announcement = new Announcement("title", "description" + societyId);
        announcements.add(announcement);

        mv.addObject("announcements", announcements);

        return mv;
    }

    private class SocietyMin {
        public Integer Id;
        public String Name;
    }
}
