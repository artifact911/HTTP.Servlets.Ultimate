package http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {

    public static final String UNIQUE_ID = "userId";

    // потокобезопасный класс
    public static final AtomicInteger counter = new AtomicInteger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        var cookie = req.getHeader("cookie");
        var cookies = req.getCookies();

        if (cookies == null || Arrays.stream(cookies)
                .filter(cookie -> UNIQUE_ID.equals(cookie.getName()))
                .findFirst()
                .isEmpty()) {
            var cookie = new Cookie(UNIQUE_ID, "1");
            // хочу чтобы эти куки шли по этому пути
            cookie.setPath("/cookies");
            // значит кука будет доступна, пока браузер не закроется
//            cookie.setMaxAge(-1);
            // или 15 * 60 = минуты сколько она будет жить в секундах
            cookie.setMaxAge(15 * 60);
            // если мы не хотим, что бы бал доступ из JS к ней, например
//            cookie.setHttpOnly(true);
            // если хотим только https
//            cookie.setSecure(true);

            resp.addCookie(cookie);
            // инкрементируем наш счетчик посещений
            counter.incrementAndGet();
        }
        resp.setContentType("text/html");
        try (var writer = resp.getWriter()) {
            writer.write(counter.get());
        }
    }
}
