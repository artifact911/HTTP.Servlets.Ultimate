package http.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

// /* получается фильтр для всех урлов
//@WebFilter("/*")
//@WebFilter(value = "/*", servletNames = {
//        "RegistrationServlet"
//})
@WebFilter(value = "/*", servletNames = {
        "RegistrationServlet"},
        initParams = {
                @WebInitParam(name = "param1", value = "paramValue")},
        dispatcherTypes = DispatcherType.REQUEST)
public class CharsetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // установили кодировку для всех респонсов и реквестов
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
}
