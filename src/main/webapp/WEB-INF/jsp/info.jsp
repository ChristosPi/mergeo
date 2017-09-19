<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>merGeo| info</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div style="text-align: center;" class="col-md-8 col-md-push-2">
            <h3><strong>Information</strong> Section</h3>
            <p>Learn about the app & find useful instructions</p>
        </div>
    </div><hr>

    <div class="row">
        <div class="col-md-6">
            <legend>merGeo:<strong>usage</strong></legend>
            <p>You could start working on a Strabon Endpoint. You have the option to create a new one or
            work on merGeo's default Endpoint. If you want to transform data and dump them to RDF, you have
            to use the GeoTriples section, which guides you step-by-step to create your finalized data. Afterwards,
            if you are working on geospatial data, you have the ability to visualize them through the Sextant section.</p>
        </div>

        <div class="col-md-6">
            <legend>merGeo:<strong>info</strong></legend>
            <p>merGeo web-application was developed within the dissertation ...</p>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>