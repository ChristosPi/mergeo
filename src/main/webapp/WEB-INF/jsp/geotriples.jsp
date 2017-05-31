<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<c:url value="/resources/css/geotriples.css"/>" rel="stylesheet">
    <title>MerGEO|Transform</title>
</head>

<body>
<div class="container-fluid">
    <div class="row">

        <%--Left Panel - Input Options--%>
        <div class="col-sm-6 col-md-6">

            <div class="panel panel-default">
                <div class="panel-heading">Choose input method</div>
                <div class="panel-body">

                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#rdatabase" data-toggle="tab">Relational Database</a></li>
                        <li><a href="#shapefile" data-toggle="tab">Shapefile</a></li>
                        <li><a href="#xmlfile" data-toggle="tab">XML files (RML only)</a></li>
                    </ul>

                    <div class="tab-content">

                        <%--Relational Database form--%>
                        <div class="tab-pane active" id="rdatabase">
                            <form role="form" method="POST" action="/geotriplesRdb">
                                <div class="form-group">
                                    <label for="rdbbaseuri">BaseURI</label>
                                    <input name="baseuri" type="text" class="form-control" id="rdbbaseuri" value="http://example.org/" required>
                                </div>
                                <div class="form-group">
                                    <label for="user">Username</label>
                                    <input name="user" type="text" class="form-control" id="user">
                                </div>
                                <div class="form-group">
                                    <label for="pass">Password</label>
                                    <input name="pass" type="password" class="form-control" id="pass">
                                </div>
                                <div class="form-group">
                                    <label for="driver">Driver</label>
                                    <input name="driver" type="text" class="form-control" id="driver">
                                </div>
                                <div class="form-group">
                                    <label for="jdbcurl">JDBC URL</label>
                                    <input name="jdbcurl" type="text" class="form-control" id="jdbcurl">
                                </div>
                                <div class="form-group">
                                    <label><input type="checkbox" name="rml" value="-rml"> RML</label>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-success">Connect</button>
                                </div>
                            </form>
                        </div>

                        <%--Shapefile form--%>
                        <div class="tab-pane fade" id="shapefile">
                            <form role="form" method="POST" action="/geotriplesShape" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="shapeinput">File input</label>
                                    <input type="file" name="inputfile" id="shapeinput" required>
                                    <p class="help-block">Choose a file to convert with GeoTriples</p>
                                </div>
                                <div class="form-group">
                                    <label for="shapebaseuri">BaseURI</label>
                                    <input name="baseuri" type="text" class="form-control" id="shapebaseuri" value="http://example.org/" required>
                                </div>
                                <div class="form-group">
                                    <label><input type="checkbox" name="rml" value="-rml"> RML</label>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-success">Upload</button>
                                </div>
                            </form>
                        </div>

                        <%--XML file form--%>
                        <div class="tab-pane fade" id="xmlfile">
                            <form role="form" method="POST" action="/geotriplesXml" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="xmlinput">File input</label>
                                    <input type="file" name="inputfile" id="xmlinput" required>
                                    <p class="help-block">Choose a file to convert with GeoTriples</p>
                                </div>
                                <div class="form-group">
                                    <label for="xmlbaseuri">BaseURI</label>
                                    <input name="baseuri" type="text" class="form-control" id="xmlbaseuri" value="http://example.org/" required>
                                </div>
                                <div class="form-group">
                                    <label for="rootpath">Rootpath</label>
                                    <input name="rootpath" type="text" class="form-control" id="rootpath" value="rootpath" required>
                                </div>
                                <div class="form-group">
                                    <label for="rootelement">Rootelement</label>
                                    <input name="rootelement" type="text" class="form-control" id="rootelement" value="rootelement" required>
                                </div>
                                <div class="form-group">
                                    <label for="namespace">Namespace</label>
                                    <input name="namespace" type="text" class="form-control" id="namespace" value="namespace" required>
                                </div>
                                    <label for="namespaces">Namespaces</label>
                                    <input name="namespaces" type="text" class="form-control" id="namespaces" value="namespaces" required>
                                <div class="form-group">
                                    <label for="xsdfile">XSD File</label>
                                </div>
                                <div class="form-group">
                                    <input name="xsdfile" type="text" class="form-control" id="xsdfile" value="XSD File" required>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-success">Upload</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%--Right Panel - Mapping output--%>
        <div class="col-sm-6 col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">Generated mapping</div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="out-map">Output code:</label>
                        <textarea readonly style="resize: none" class="form-control" rows="15" id="out-map">${outmap_code}</textarea>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>