package http.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Stream;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // можно получить один или список параметров
        var paramValue = req.getParameter("param");
        var parameterMap = req.getParameterMap();


        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("token", "12345");
        // кодировку лучше пердавать явно так либо:
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = resp.getWriter()) {
            writer.write("<h1>Hello from first servlet</h2>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // отправили тут постманом запрос и отправили в теле вкладеой www-... параметры
//        var parameterMap = req.getParameterMap();

        // отправили постманом текст в body. Если ты биты. то инпутСтрим юзали бы. Отправили текст в трех строках,
        // можем читать просто реадером
//        req.getInputStream()
        try (var reader = req.getReader();
             var lines = reader.lines()) {

            lines.forEach(System.out::println);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
