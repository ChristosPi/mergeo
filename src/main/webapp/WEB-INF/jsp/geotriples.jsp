<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<c:url value="/resources/css/geotriples.css" />" rel="stylesheet">
    <title>MerGEO|Transform</title>
</head>

<body>
    <form method="POST" action="/geotriplesFile" enctype="multipart/form-data">
        File to upload: <input type="file" name="file" required>
        <input type="submit" value="Upload"> Press here to upload the file!
    </form>
</body>
</html>