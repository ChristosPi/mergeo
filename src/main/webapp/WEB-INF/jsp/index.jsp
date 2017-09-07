<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
    <title>MerGEO|Home</title>
</head>

<body>
    <article>
        <h2>Welcome to MerGeo Web-Application</h2>
        <p>Follow the Navbar order, you must!</p>
        <img src="./../../resources/img/flow.png" width="550" height="415">
    </article>

    <jsp:include page="footer.jsp"/>
</body>
</html>