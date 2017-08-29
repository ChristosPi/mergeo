<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
    <title>MerGEO|Error</title>
</head>
<body>
    <article>
        <h4>Something went wrong... OR DID YOU MAKE IT GO WRONG?</h4>
        <h5>Anyway, go away from here! NOW!</h5>
        <img src="./../../resources/img/error.png" alt="..." class="img-responsive center-block"/>
    </article>

    <jsp:include page="footer.jsp"/>
</body>
</html>
