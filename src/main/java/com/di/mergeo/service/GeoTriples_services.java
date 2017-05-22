package com.di.mergeo.service;


public class GeoTriples_services {

    public String[] geotriplesMapString(String sourcefile){
        String[] map_string = {"-o", "boulouki.tll", sourcefile};
        return map_string;
    }
}
