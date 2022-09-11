package http.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // можем получить хедер по ключу
       req.getHeader("user-agent");
       // или получить все
        var headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            var header = headerNames.nextElement();
            System.out.println(header);
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("token", "12345");
        // кодировку лучше пердавать явно так либо:
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = resp.getWriter()) {
            writer.write("<h1>Hello from first servlet</h2>");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
