package com.di.mergeo.controller;


import com.di.mergeo.PostgreSQLJDBC;
import com.di.mergeo.model.EndpointModel;
import com.di.mergeo.service.EndpointService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@Controller
public class EndpointController {

    @RequestMapping(value = "/endpoint_create", method = RequestMethod.POST)
    public ModelAndView endpointCreate(@ModelAttribute("SpringWeb")EndpointModel endmodel, ModelMap model) throws SQLException, ClassNotFoundException, IOException, InterruptedException {

        PostgreSQLJDBC.dbCreate(endmodel);
        EndpointService.strabonDeploy(endmodel);

        TimeUnit.SECONDS.sleep(10);

        EndpointService.strabonSetCredentials(endmodel);

        ModelAndView mav = new ModelAndView("endpoint_done");
        return mav;
    }
}
