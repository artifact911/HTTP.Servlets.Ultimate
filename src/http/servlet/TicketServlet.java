package http.servlet;

import http.service.TicketService;
import http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var flightId = Long.valueOf(req.getParameter("flightId"));
        req.setAttribute("tickets", ticketService.findAllByFlightId(flightId));


        // мы добавим это при помощи директивы page на нашей jps
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // теперь нам это не нужно
//        try (var printWriter = resp.getWriter()) {
//            printWriter.write("<h1>Купленные билеты</h2>");
//            printWriter.write("<ul>");
//            ticketService.findAllByFlightId(flightId).forEach(ticketDto -> printWriter.write("""
//                    <li>
//                        %s
//                    </li>
//                    """.formatted(ticketDto.getSeatNo())));
//            printWriter.write("</ul>");
//        }

        // перенаправим запрос и перенесем tickets.jsp в закрытый WEB-INF/jsp
        req.getRequestDispatcher(JspHelper.getPath("tickets"))
                .forward(req, resp);
    }
}
