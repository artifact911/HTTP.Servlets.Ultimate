package http.servlet;

import http.dto.CreateUserDto;
import http.exception.ValidationException;
import http.service.UserService;
import http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;

// @MultipartConfig
// location - куда будут сохраняться принятые файлы у нас на серве
// maxFileSize - максимальный размер передаваемого файла
// maxRequestSize - максимальный размер реквеста
// fileSizeThreshold - файлы ТОЛЬКО большего размера будут сохраняться на диск в location, в байтах. Меньше - будет держать в inMemory
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", List.of("USER", "ADMIN"));
        req.setAttribute("genders", List.of("MALE", "FEMALE"));

        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    // добавим в БД пользователю поле image. Это поле будет хранить путь к нашей картинке, а не массив байт
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        // так мы можем передавать из req картинку
        // но этого тож недостаточно, тк по-умолчанию сервлеты не могут работать с part
        // поэтому нужно использовать аннотацию @MultipartConfig
        var image = req.getPart("image");


        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .image(image)
                .birthday(req.getParameter("birthday"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .gender(req.getParameter("gender"))
                .build();

        try {
            userService.create(userDto);
            resp.sendRedirect("/login");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
