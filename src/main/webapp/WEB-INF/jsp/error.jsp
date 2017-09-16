<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
    <title>merGeo| Error</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div style="text-align: center;" class="col-md-8 col-md-push-2">
            <h3>Oops! An <strong>error</strong> occured...</h3>
            <p>Something went wrong... OR DID YOU MAKE IT GO WRONG?<br>Anyway, return to Homepage, NOW!</p>
        </div>
    </div>
    <hr>
    <div class="row">
        <article>
            <img src="./../../resources/img/error.png" alt="..." class="img-responsive center-block"/>
        </article>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
