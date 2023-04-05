package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.validation.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoRegistration implements Command {

    private final IUserService service = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");

        try {
            if (service.registration(login, password, name, surname, email, birthday)) {
                session.setAttribute("user", "active");
                session.setAttribute("guest", "not active");
                session.setAttribute("info_message", "Registration was successful");
                response.sendRedirect("controller?command=go_to_news_list");
            } else {
                session.setAttribute("info_message", "Registration failed, try again");
                response.sendRedirect("controller?command=go_to_registration");
            }

        } catch (ServiceException e) {
            response.sendRedirect("controller?command=go_to_base_page");
        } catch (ValidationException e) {
            String[] errors = e.getMessage().split(";");
            session.setAttribute("validationError", errors);
            response.sendRedirect("controller?command=go_to_error_page");
        }
    }

}
