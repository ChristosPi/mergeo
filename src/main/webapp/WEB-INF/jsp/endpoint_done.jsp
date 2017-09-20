<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<s:url value="/resources/css/endpoint.css"/>" rel="stylesheet">
    <title>mG|Strabon</title>

    <script>
        function reset_query() {
            document.getElementById("querytextarea").value = "PREFIX lgd:<http://linkedgeodata.org/triplify/>\n" +
                "PREFIX lgdgeo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\n" +
                "PREFIX lgdont:<http://linkedgeodata.org/ontology/>\n" +
                "PREFIX geonames:<http://www.geonames.org/ontology#>\n" +
                "PREFIX clc: <http://geo.linkedopendata.gr/corine/ontology#>\n" +
                "PREFIX gag: <http://geo.linkedopendata.gr/greekadministrativeregion/ontology#>\n" +
                "PREFIX geo: <http://www.opengis.net/ont/geosparql#>\n" +
                "PREFIX geof: <http://www.opengis.net/def/function/geosparql/>\n" +
                "PREFIX geor: <http://www.opengis.net/def/rule/geosparql/>\n" +
                "PREFIX strdf: <http://strdf.di.uoa.gr/ontology#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX uom: <http://www.opengis.net/def/uom/OGC/1.0/>" ;
        }
    </script>

    <script type="text/javascript">
        function toggleMe(a) {
            var e = document.getElementById(a);
            if (!e) {
                return true;
            }
            if (e.style.display == "none") {
                e.style.display = "block";
            } else {
                e.style.display = "none";
            }
            return true;
        }
    </script>

    <script>
        $(document).ready(function() {
            var showChar = 100;
            var ellipsestext = "...";
            var moretext = "more";
            var lesstext = "less";
            $('.more').each(function() {
                var content = $(this).html();

                if(content.length > showChar) {

                    var c = content.substr(0, showChar);
                    var h = content.substr(showChar-1, content.length - showChar);

                    var html = c + '<span class="moreelipses">'+ellipsestext+'</span>&nbsp;<span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a href="" class="morelink">'+moretext+'</a></span>';

                    $(this).html(html);
                }

            });

            $(".morelink").click(function(){
                if($(this).hasClass("less")) {
                    $(this).removeClass("less");
                    $(this).html(moretext);
                } else {
                    $(this).addClass("less");
                    $(this).html(lesstext);
                }
                $(this).parent().prev().toggle();
                $(this).prev().toggle();
                return false;
            });
        });
    </script>

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
                    <div class="col-md-6 col-md-offset-3">
                        <div style="text-align: center;" class="alert alert-dismissible alert-success" role="alert" style="text-align: center;">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>[ <i class="fa fa-exclamation-circle" aria-hidden="true"></i> ] Data store status:</strong> Done
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div style="text-align: center;" class="alert alert-dismissible alert-danger" role="alert" style="text-align: center;">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>[ <i class="fa fa-exclamation-circle" aria-hidden="true"></i> ] Data store status:</strong> Failed
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
            <div class="row">
                <span style="text-align: center;"><h4>Reset Query:</h4></span>
                <button type="button" onclick="reset_query();"class="btn btn-warning col-md-12" style="margin-bottom:4px;white-space: normal;">Reset Query Textarea</button>
            </div>
        </div>

        <div class="col-md-9">
            <span style="text-align: center;"><h4>Endpoint: <strong>${workEndpoint.endpointname}</strong></h4></span>
            <form action="/do_query" method="post">
                <div class="form-group">
                    <textarea id="querytextarea" name="query" class="form-control" rows="20" columns="15" style="text-align: justify;"><c:choose><c:when test="${empty endpointResults}">
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
                                <option value="GEOJSON">GeoJSON</option>
                            </select>
                        </div>

                         <%--Both query and update button-group --%>
                        <div class="col-md-9">
                            <div class="btn-group pull-right">
                                <button type="submit" class="btn btn-primary btn-lg ">Execute <strong>Query</strong></button>
                                <button type="button" class="btn btn-primary btn-lg disabled">Execute <strong>Update</strong></button>
                            </div>
                        </div>

                        <%-- Just query button --%>
                        <%--<div class="col-md-5 col-md-offset-4">--%>
                            <%--<button type="submit" class="btn btn-primary btn-block btn-lg">Execute <strong>Query</strong></button>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <hr>
    <div class="row">
        <c:if test="${not empty endpointResults.getStatusText()}">
            <div class="alert alert-warning" role="alert">${endpointResults.getStatusText()}</div>
        </c:if>
        <c:if test="${not empty endpointResults.getResponse()}">
            <legend>Endpoint Results:</legend>
            <c:choose>
                <c:when test="${out_format == 'HTML'}">
                    <div class="col-md-12" align="center">${endpointResults.getResponse()}</div>
                </c:when>
                <c:otherwise>
                    <div class="col-md-12">
                        <pre style="font-size: 12px;"><c:out value="${endpointResults.response}"/></pre>
                    </div>
                </c:otherwise>
            </c:choose>
            <%--<div class="table-responsive">--%>
                <%--<table id="resulttable" class="table table-bordered" style="text-align: left; font-size: 12px;" cellspacing="10">--%>
                <%--${endpointResults.getResponse()}--%>
                <%--</table>--%>
            <%--</div>--%>
        </c:if>
        <br/>
        <br/>
        <br/>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
