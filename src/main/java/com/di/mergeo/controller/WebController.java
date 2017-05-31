package com.di.mergeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
    public String geotriples_successPage(@ModelAttribute("fileName") String name,
                                         @ModelAttribute("outmap_code") String outmapcode) {
        return "geotriples";
    }
}