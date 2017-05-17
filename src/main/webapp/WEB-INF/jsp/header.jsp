<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="bootstrap.jsp"/>
    <link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">
    <title>MerGEO</title>
</head>
<body>
    <section class="container-fluid">

        <header class="row">
            <div class="col-md-6">
                <a href="/"><h1>MerGEO</h1></a>
            </div>
        </header>
        <header class="row">
            <h2 align="center">Welcome to MerGEO</h2>
        </header>

        <nav class="navbar navbar-inverse">
            <nav class="navbar-header">
                <a class="navbar-brand active" href="/">  MerGEO  </a>
            </nav>
            <ul class="nav navbar-nav">
                <li><a href="/geotriples">1.Transformation</a></li>
                <li><a href="#">2.Endpoint</a></li>
                <li><a href="#">3.Sextant</a></li>
            </ul>
        </nav>
    </section>
</body>
</html>
