package com.di.mergeo.controller;


import com.di.mergeo.PostgreSQLJDBC;
import com.di.mergeo.model.EndpointModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class EndpointController {

    @RequestMapping(value = "/endpoint_create", method = RequestMethod.POST)
    public ModelAndView endpointCreate(@ModelAttribute("SpringWeb")EndpointModel endmodel, ModelMap model) throws SQLException, ClassNotFoundException {

        PostgreSQLJDBC.dbCreate(endmodel);
        ModelAndView mav = new ModelAndView("endpoint_done");
        return mav;
    }
}
