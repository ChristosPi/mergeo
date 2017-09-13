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
        <div style="text-align: center;" class="col-md-8 col-md-push-2">
            <h3><strong>Strabon</strong> Section</h3>
            <p>The selected Strabon Endpoint is up and running. Let's use it!</p>
        </div>
    </div><hr>
    <c:if test="${not empty storeStatus}">
        <c:choose>
            <c:when test="${storeStatus == true}">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="alert alert-success" role="alert" style="text-align: center;">
                            <strong>Data store status:</strong> OK
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="alert alert-danger" role="alert" style="text-align: center;">
                            <strong>Data store status:</strong> Failed
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </c:if>
    <div class="row">
        <div class="col-md-3">
            <div class="row">
                <span style="text-align: center;"><h4>Store data:</h4></span>
                <form action="/do_store" method="post" >
                    <button type="submit" class="btn btn-info col-md-12" style="margin-bottom:4px;white-space: normal;">Open 'Store' interface</button>
                </form>
            </div>
            <br>
            <div class="row">
                <span style="text-align: center;"><h4>Example Queries:</h4></span>
                <form action="/endpoint/exquery" method="post" >
                    <input type="hidden" value="1" name="example"/>
                    <button type="submit" class="btn btn-default col-md-12" style="margin-bottom:4px;white-space: normal;">Find all the triples in the dataset.</button>
                </form>
            </div>
            <div class="row">
                <form action="/endpoint/exquery" method="post" >
                    <input type="hidden" value="2" name="example"/>
                    <button type="submit" class="btn btn-default col-md-12" style="margin-bottom:4px;white-space: normal;">Select all the distinct subjects that appear in the dataset.</button>
                </form>
            </div>
            <div class="row">
                <form action="/endpoint/exquery" method="post" >
                    <input type="hidden" value="3" name="example"/>
                    <button type="submit" class="btn btn-default col-md-12" style="margin-bottom:4px;white-space: normal;">Find the number of triples that appear in the dataset.</button>
                </form>
            </div>
            <div class="row">
                <form action="/endpoint/exquery" method="post" >
                    <input type="hidden" value="4" name="example"/>
                    <button type="submit" class="btn btn-default col-md-12" style="margin-bottom:4px;white-space: normal;">Present the first ten triples of the dataset.</button>
                </form>
            </div>
        </div>

        <div class="col-md-9">
            <span style="text-align: center;"><h4>Endpoint: <strong>${workEndpoint.endpointname}</strong></h4></span>
            <form action="/do_query" method="post">
                <div class="form-group">
                    <textarea name="query" class="form-control" rows="18" columns="15" style="text-align: justify;"><c:choose><c:when test="${empty endpointResults}">
PREFIX lgd:<http://linkedgeodata.org/triplify/>
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
PREFIX uom: <http://www.opengis.net/def/uom/OGC/1.0/>
</c:when><c:otherwise>${query}</c:otherwise></c:choose></textarea>
                </div>
                <div class="form-group" style="text-align: right;">
                    <div class="row">
                        <div class="col-md-3" style="text-align: left;">
                            <label>Output Format:</label>
                            <select class="form-control" name="format">
                                <option value="HTML">HTML</option>
                                <option value="XML">SPARQL/XML</option>
                                <option value="JSON">GeoJSON</option>
                            </select>
                        </div>

                        <div class="col-md-5 col-md-offset-4">
                            <button type="submit" class="btn btn-success btn-lg">Execute Query</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <br/>
            <c:if test="${not empty endpointResults.getStatusText()}">
                <div class="alert alert-warning" role="alert">${endpointResults.getStatusText()}</div>
            </c:if>
            <c:if test="${not empty endpointResults.getResponse()}">
                <span><h4>Endpoint Results:</h4></span>
                <div class="table-responsive">
                    <table id="resulttable" class="table table-bordered" style="text-align: left; font-size: 12px;" cellspacing="10">
                    ${endpointResults.getResponse()}
                    </table>
                </div>
            </c:if>
            <br/>
            <br/>
            <br/>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
