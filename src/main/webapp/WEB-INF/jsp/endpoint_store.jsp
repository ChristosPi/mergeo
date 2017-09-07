<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<s:url value="/resources/css/endpoint.css"/>" rel="stylesheet">
    <title>MerGEO|Endpoint</title>
</head>
<body>

<div class="container">
    <div class="row">
        <span style="text-align: center;"><h4>Endpoint: <strong>${workEndpoint.endpointname}</strong></h4></span>
        <div class="col-md-12">

            <form class="form-horizontal" method="post" action="/endpoint/direct_store" style="border:1px solid #cecece;">
                <%-- Direct input store --%>
                <br><label class="col-md-offset-5">Direct store of RDF Data:</label><br><br><br>
                <div class="form-group">
                    <label class="control-label col-md-2" for="graph">Graph:</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" rows="1" id="graph" name="graph"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2" for="dinput">Direct Input:</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" rows="5" id="dinput" name="dinput" required></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2" for="rdfformat">RDF Format:</label>
                    <div class="col-sm-8">
                        <select class="form-control" name="rdfformat" id="rdfformat" required>
                            <%-- TODO - We can add more formats --%>
                            <option value="NTRIPLES">N-Triples</option>
                            <option value="XML">RDF/XML</option>
                            <option value="TURTLE">Turtle</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <div class="checkbox">
                            <label><input name="inference" type="checkbox"> Inference</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-8">
                        <button type="submit" class="btn btn-default btn-block">Store Direct Input</button>
                    </div>
                </div>
            </form>

            <%-- Store from file URI --%>

            <form class="form-horizontal" method="post" action="/endpoint/uri_store" style="border:1px solid #cecece;">
                <br><label class="col-md-offset-5">Store RDF data via URI:</label><br><br><br>
                <div class="form-group">
                    <label class="control-label col-md-2" for="uri_graph">Graph:</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" rows="1" id="uri_graph" name="uri_graph"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2" for="uri_input">URI input:</label>
                    <div class="col-sm-8">
                        <input class="form-control" type="text" name="uri_input" id="uri_input" placeholder="e.g., file:///home/user/example.nt">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2" for="uri_rdfformat">RDF Format:</label>
                    <div class="col-sm-8">
                        <select class="form-control" name="uri_rdfformat" id="uri_rdfformat" required>
                            <%-- TODO - We can add more formats --%>
                            <option value="NTRIPLES">N-Triples</option>
                            <option value="XML">RDF/XML</option>
                            <option value="TURTLE">Turtle</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-8">
                        <button type="submit" class="btn btn-default btn-block">Store from URI</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>