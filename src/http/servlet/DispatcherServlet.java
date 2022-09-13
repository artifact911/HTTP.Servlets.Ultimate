package http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var requestDispatcher = req.getRequestDispatcher("/flights");

//        req.setAttribute("1", "234");
//        requestDispatcher.forward(req, resp);

        // переправили в /flights
        // тут нужно понять: в flightsServlet отработает поток и закроется и сразу после этого отправится респонс.
        // даже если ниже этого года что-то написать, то оно не сработает
        // мы можем не закрывать поток при помощи трай с ресурсами и тогда наш респонс не полетит сразу после
        // закрытия потока
        // но с учетом того, что это forward, мы не оправим из этого сервлета больше ничего, тк это сделает flightServlet
//        req.getRequestDispatcher("/flights")
//                .forward(req, resp);
        // можно вызвать Dispatcher иначе getServletContext().getRequestDispatcher() но это делает и req обращаясь
        // к сервлетКонтексту


        // теперь include
        // теперь все что мы запишем до или после include имеет значение.
//        req.getRequestDispatcher("/flights")
//               .include(req, resp);

        // теперь эти строчик дописались, но мы видим крокозябры, т.к. у нас отвалились хедеры из flightServlet и
        // нам нужно устанавливать хедеры в этом сервлете
//        var writer = resp.getWriter();
//        writer.write("Hello 2");


        // теперь redirect и он относится к респонсу
        // теперь в урле вы увидим /flights тк с ним мы теперь и работаем. До этого был dispatcher, хотя по сути
        // данные нам были переданы из flightsServlet
        // в девТулз можно увидеть, что нам вернулся статусКод 302 и нас перенаправило в /flights указанный в хедере
        // респонса Location
        resp.sendRedirect("/flights");
    }
}
