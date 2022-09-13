<%@ page import="http.service.TicketService" %>
<%@ page import="http.dto.TicketDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%--    <h1>Hello World!</h1>--%>
<%--напишем среплет, но так делать не нужно--%>
    <h1>Купленные билеты</h1>
        <ul>
    <%
        Long flightId = Long.valueOf(request.getParameter("flightId"));
        List<TicketDto> tickets = TicketService.getInstance().findAllByFlightId(flightId);
        for (TicketDto ticket : tickets) {
            out.write(String.format("<li>%s</li>", ticket.getSeatNo()));
        }
    %>
        </ul>
</body>
</html>

<%--можно переопределять методы и для этого используются декларейшены--%>
    <%!
    public void jspInit() {
         System.out.println("Hello world!");
    }
    %>