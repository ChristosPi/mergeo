package com.di.mergeo.strabonEndpointClient;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.openrdf.query.resultio.TupleQueryResultFormat;

import java.nio.charset.Charset;
import java.util.*;

public class stSPARQLQueryResultFormat extends TupleQueryResultFormat {
    public static final com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat XML = new com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat("XML", Arrays.asList("application/sparql-results+xml", "application/xml"), Charset.forName("UTF-8"), Arrays.asList("xml"));
    public static final com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat KML = new com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat("KML", Arrays.asList("application/vnd.google-earth.kml+xml", "application/kml"), Charset.forName("UTF-8"), Arrays.asList("kml"));
    public static final com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat KMZ = new com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat("KMZ", Arrays.asList("application/vnd.google-earth.kmz", "application/kmz"), Charset.forName("UTF-8"), Arrays.asList("kmz"));
    public static final com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat GEOJSON = new com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat("GeoJSON", Arrays.asList("application/json", "application/geojson"), Charset.forName("UTF-8"), Arrays.asList("json"));
    public static final com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat TSV = new com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat("TSV", Arrays.asList("text/tab-separated-values"), Charset.forName("UTF-8"), Arrays.asList("tsv"));
    public static final com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat HTML = new com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat("HTML", Arrays.asList("text/html"), Charset.forName("UTF-8"), Arrays.asList("html", "htm"));
    public static final com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat PIECHART = new com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat("PIECHART", Arrays.asList("text/plain"), Charset.forName("UTF-8"), Arrays.asList("piechart", "piechart"));
    public static final com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat AREACHART = new com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat("AREACHART", Arrays.asList("text/plain"), Charset.forName("UTF-8"), Arrays.asList("areachart", "areachart"));
    public static final com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat COLUMNCHART = new com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat("COLUMNCHART", Arrays.asList("text/plain"), Charset.forName("UTF-8"), Arrays.asList("columnchart", "columnchart"));
    private static final List<com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat> VALUES = new ArrayList(6);

    public static void register(com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat format) {
        TupleQueryResultFormat.register(format);
        VALUES.add(format);
    }

    public static TupleQueryResultFormat valueOf(String formatName) {
        Iterator i$ = values().iterator();

        TupleQueryResultFormat format;
        do {
            if (!i$.hasNext()) {
                return null;
            }

            format = (TupleQueryResultFormat)i$.next();
        } while(!format.getName().equalsIgnoreCase(formatName));

        return format;
    }

    public static Collection<TupleQueryResultFormat> values() {
        return TupleQueryResultFormat.values();
    }

    public static com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat forMIMEType(String mimeType) {
        return forMIMEType(mimeType, (com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat)null);
    }

    public static com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat forMIMEType(String mimeType, com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat fallback) {
        return (com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat)matchMIMEType(mimeType, VALUES, fallback);
    }

    public stSPARQLQueryResultFormat(String name, String mimeType, String fileExt) {
        super(name, mimeType, fileExt);
    }

    public stSPARQLQueryResultFormat(String name, String mimeType, Charset charset, String fileExt) {
        super(name, mimeType, charset, fileExt);
    }

    public stSPARQLQueryResultFormat(String name, Collection<String> mimeTypes, Charset charset, Collection<String> fileExtensions) {
        super(name, mimeTypes, charset, fileExtensions);
    }

    static {
        register(XML);
        register(KML);
        register(KMZ);
        register(GEOJSON);
        register(TSV);
        register(HTML);
        register(PIECHART);
        register(AREACHART);
        register(COLUMNCHART);
    }
}

