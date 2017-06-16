package com.di.mergeo.controller;

import com.di.mergeo.model.InputModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "index";
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    @RequestMapping(value = "/geotriples", method = RequestMethod.GET)
    public ModelAndView geotriples() {
        return new ModelAndView("geotriples", "command", new InputModel());
    }
    @RequestMapping(value = "/geotriples_success", method = RequestMethod.GET)
    public String geotriples_success() {
        return "geotriples";
    }
    @RequestMapping(value = "/endpoint", method = RequestMethod.GET)
    public String endpoint() {
        return "endpoint";
    }
    @RequestMapping(value = "/sextant", method = RequestMethod.GET)
    public String sextant() {
        return "sextant";
    }
}