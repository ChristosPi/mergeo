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
            <form method="POST" action="/do_store">
                <input type="hidden" value="${endpointName}" name="endpointName" />
                <button type="submit" class="btn btn-success">Store something!</button>
            </form>
            <form action="/do_query" method="post">
                <input type="hidden" value="<%request.getAttribute("endpointName");%>" name="endpointName" />
                <button type="submit" class="btn btn-success">Query something!</button>
            </form>
            <form action="/do_update" method="post">
                <input type="hidden" value="<%request.getAttribute("endpointName");%>" name="endpointName" />
                <button type="submit" class="btn btn-success">Update something!</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
