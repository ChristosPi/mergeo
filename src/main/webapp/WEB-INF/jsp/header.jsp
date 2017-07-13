<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="bootstrap.jsp"/>
    <link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">
    <title>MerGeo</title>
</head>
<body>
    <section class="container">

        <header class="row">
            <div class="col-md-6">
                <a href="/"><h1>MerGeo</h1></a>
            </div>
        </header>
        <header class="row">
            <h2 align="center">Welcome to MerGeo</h2>
        </header>

        <nav class="navbar navbar-inverse">
            <nav class="navbar-header">
                <a class="navbar-brand active" href="/">MerGe<span class="glyphicon glyphicon-globe"></span></a>
            </nav>
            <ul class="nav navbar-nav">
                <li><a href="/geotriples"><span class="glyphicon glyphicon-transfer"></span> 1.Transformation</a></li>
                <li><a href="/endpoint"><span class="glyphicon glyphicon-import"></span> 2.Endpoint</a></li>
                <li><a href="/sextant"><span class="glyphicon glyphicon-map-marker"></span> 3.Sextant</a></li>
            </ul>
        </nav>
    </section>
</body>
</html>
