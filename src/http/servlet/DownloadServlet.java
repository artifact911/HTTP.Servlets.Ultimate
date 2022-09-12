package http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// теперь если запустить tomcat и перейти на localhost/download мы скачаем файл
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // чтобы скачать файл с сервера, нам нужно передать специальный хедер в респонсе
        resp.setHeader("Content-Disposition", "attachment; filename=\"filename.txt\"");
        // перепишем этот хедер для чтения json
//        resp.setContentType("text/plain");
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // отправим текст на хардкоде
//        try (var printWriter = resp.getWriter()) {
//            printWriter.write("Data from servlet!");
//        }

        // попробуем прочитать из файла
//        Files.readAllBytes(Path.of("resources", "first.json"));
        // этот способ не сработает, т.к. HTTP-сервлет-стартер у нас не самостоятельное приложение. Мы заворачиваем
        // его в war-архив и деплоим на томкэт. ТомКэт настоящее java-приложение и у него идет relative-path от его
        // директории, а не от нашего проекта.
        // Решение:
        // либо мы указываем абсолютный путь к файлу
        // либо указываем папку с ресурсами, как ResourcesRoot и эта папка запишется в нашу war-ку

        try (var outputStream = resp.getOutputStream();
             var stream = DownloadServlet.class.getClassLoader().getResourceAsStream("first.json");) {
            outputStream.write(stream.readAllBytes());
        }
    }
}
