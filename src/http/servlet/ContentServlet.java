package http.servlet;

import http.dto.FlightDto;
import http.service.FlightService;
import http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toMap;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // установим артибуты чтобы достать из при помощи EL (expression language) - lesson42
        List<FlightDto> flightDtos = flightService.findAll();
        req.setAttribute("flights", flightDtos);

        // тут установили в sessionScope
        req.getSession().setAttribute("flightsMap", flightDtos.stream()
                .collect(toMap(FlightDto::getId, FlightDto::getDescription)));

        req.getRequestDispatcher(JspHelper.getPath("content"))
                .forward(req, resp);
    }
}
