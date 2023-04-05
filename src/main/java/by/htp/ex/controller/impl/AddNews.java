package by.htp.ex.controller.impl;

import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.validation.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AddNews implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    private static final String NEWS_TITLE = "title";
    private static final String NEWS_DATE = "date";
    private static final String NEWS_BRIEF = "brief";
    private static final String NEWS_CONTENT = "content";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            Integer usersId = (Integer) request.getSession().getAttribute("usersId");
            newsService.addNews(request.getParameter(NEWS_TITLE), request.getParameter(NEWS_DATE),
                    request.getParameter(NEWS_BRIEF), request.getParameter(NEWS_CONTENT), usersId);

            response.sendRedirect("controller?command=go_to_news_list");
        } catch (ServiceException | ValidationException e) {
            String[] errors = e.getMessage().split(";");
            session.setAttribute("validationError", errors);
            response.sendRedirect("controller?command=go_to_error_page");
        }
    }
}
