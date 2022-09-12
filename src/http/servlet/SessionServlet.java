package http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sessions")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();

        // проверит, новая ли сессия и первый раз будет true.
        // посмотрим куки и там будет лежать наша кука JSESSIONID c ID сессии.
        // обновим страницу и на этот раз isNew() вернет false.
        System.out.println(session.isNew());
    }
}
