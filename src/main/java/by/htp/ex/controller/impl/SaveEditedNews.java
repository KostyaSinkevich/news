package by.htp.ex.controller.impl;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.validation.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SaveEditedNews implements Command {

    private static final String NEWS_TITLE = "title";
    private static final String NEWS_DATE = "date";
    private static final String NEWS_BRIEF = "brief";
    private static final String NEWS_CONTENT = "content";

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            newsService.update(id, request.getParameter(NEWS_TITLE), request.getParameter(NEWS_DATE),
                    request.getParameter(NEWS_BRIEF), request.getParameter(NEWS_CONTENT));
            News news = newsService.findById(id);
            request.setAttribute("news", news);

            response.sendRedirect("controller?command=go_to_view_news&id=" + id);
        } catch (ServiceException | ValidationException e) {
            response.sendRedirect("controller?command=go_to_error_page");
        }
    }
}