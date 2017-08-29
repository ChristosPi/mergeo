<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<s:url value="/resources/css/endpoint.css"/>" rel="stylesheet">
    <title>MerGEO|Endpoint</title>
</head>
<body>

<div class="container">
    <div class="row">

        <div class="col-md-3">
            <form action="/do_store" method="post" >
                <input type="text" class="form-control" value="${endpoint.endpointname}" name="endpointName" />
                <button type="submit" class="btn btn-success">Store something!</button>
            </form>
            <form action="#" method="post">
                <input type="text" class="form-control" value="${endpoint.endpointname}" name="endpointName" />
                <button type="submit" class="btn btn-success">Query something!</button>
            </form>
            <form action="#" method="post">
                <input type="text" class="form-control" value="${endpoint.endpointname}" name="endpointName" />
                <button type="submit" class="btn btn-success">Update something!</button>
            </form>
        </div>

        <div class="col-md-9">
            <span style="text-align: center;"><h4>Endpoint: ${endpoint.endpointname}</h4></span>
            <form action="/do_query" method="post">
                <div class="form-group">
                    <textarea name="query" class="form-control" rows="15" columns="15" style="text-align: justify;"><c:choose><c:when test="${1 == 1}">PREFIX lgd:<http://linkedgeodata.org/triplify/>
PREFIX lgdgeo:<http://www.w3.org/2003/01/geo/wgs84_pos#>
PREFIX lgdont:<http://linkedgeodata.org/ontology/>
PREFIX geonames:<http://www.geonames.org/ontology#>
PREFIX clc: <http://geo.linkedopendata.gr/corine/ontology#>
PREFIX gag: <http://geo.linkedopendata.gr/greekadministrativeregion/ontology#>
PREFIX geo: <http://www.opengis.net/ont/geosparql#>
PREFIX geof: <http://www.opengis.net/def/function/geosparql/>
PREFIX geor: <http://www.opengis.net/def/rule/geosparql/>
PREFIX strdf: <http://strdf.di.uoa.gr/ontology#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX uom: <http://www.opengis.net/def/uom/OGC/1.0/></c:when><c:otherwise>TODO - print given query</c:otherwise></c:choose></textarea>
                </div>
                <div class="form-group" style="text-align: right;">
                    <div class="row">
                        <div class="col-md-3" style="text-align: left;">
                            <label>Endpoint form</label>
                            <select class="form-control" name="format">
                                <option value="HTML">HTML</option>
                                <option value="XML">SPARQL/XML</option>
                            </select>
                        </div>

                        <div class="col-md-5 col-md-offset-4">
                            <button type="submit" class="btn btn-default btn-lg">Run, Forest, Run!</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="row">
        <br>
        <h3>TODO - Here should be the results</h3>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
