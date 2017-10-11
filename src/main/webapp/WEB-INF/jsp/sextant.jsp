<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<s:url value="/resources/css/sextant.css"/>" rel="stylesheet">
    <title>mG|Sextant</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div style="text-align: center;" class="col-md-8 col-md-push-2">
            <h3><strong>Sextant</strong> Section</h3>
            <p>Here you can have full, exclusive access on Sextant application. Enjoy!</p>
        </div>
    </div>
    <hr>

    <div class="embed-responsive embed-responsive-16by9">
        <iframe class="embed-responsive-item" src="http://localhost:8080/SextantOL3" allowfullscreen></iframe>
    </div>

</div>

    <jsp:include page="footer.jsp"/>
</body>
</html>