package by.htp.ex.controller.impl;

import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteNews implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] selectedNewsId = request.getParameterValues("id");
            newsService.deleteSelectedNews(selectedNewsId);

            response.sendRedirect("controller?command=go_to_news_list");
        } catch (ServiceException e) {
            response.sendRedirect("controller?command=go_to_error_page");
        }
    }
}
