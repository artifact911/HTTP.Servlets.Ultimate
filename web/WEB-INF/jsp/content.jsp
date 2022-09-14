<%--
  Created by IntelliJ IDEA.
  User: anduser
  Date: 14.09.22
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="header.jsp"%>
    <div>

<%--        http://localhost:8081/content?id=5&test=hello--%>
        <span>Content. Русский</span>

        <p>Size: ${requestScope.flights.size()}</p>
        <p>Id: ${requestScope.flights.get(0).id}</p>
        <p>Id 2: ${requestScope.flights[1].id}</p>
        <p>Map id 2: ${sessionScope.flightsMap[1]}</p>
        <p>JSESSION id: ${cookie["JSESSIONID"].value}, unique identifier</p>
        <p>Header: ${header["Cookie"]}</p>
        <p>Param id: ${param.id}</p>
        <p>Param test: ${param.test}</p>
        <p>Empty list: ${not empty requestScope.flights}</p>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>
