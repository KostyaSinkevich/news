package by.htp.ex.controller.impl;

import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DoSignIn implements Command {

    private final IUserService service = ServiceProvider.getInstance().getUserService();

    private static final String JSP_LOGIN_PARAM = "login";
    private static final String JSP_PASSWORD_PARAM = "password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        String login;
        String password;

        login = request.getParameter(JSP_LOGIN_PARAM);
        password = request.getParameter(JSP_PASSWORD_PARAM);

        try {

            String role = service.signIn(login, password);

            if (!role.equals("guest")) {
                session.setAttribute("user", "active");
                session.setAttribute("guest", "not active");
                session.setAttribute("usersId", service.getUsersId(login, password));
                session.setAttribute("role", role);
                response.sendRedirect("controller?command=go_to_news_list");
            } else {
                session.setAttribute("user", "not active");
                session.setAttribute("authenticationError", "wrong login or password");
                response.sendRedirect("controller?command=go_to_base_page");
            }

        } catch (ServiceException e) {
            response.sendRedirect("controller?command=go_to_error_page");
        }
    }
}
