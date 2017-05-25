package com.di.mergeo.service;


import java.io.File;

public class GeotriplesService {

    public String[] geotriplesMapping(String sourcename, String sourcefile, String outmapPath){
        outmapPath += File.separator;
        String[] map_string = {"generate_mapping", "-b", "http://example.org/", "-o",
                outmapPath+sourcename+"-map.tll", sourcefile};
        return map_string;
    }
}
