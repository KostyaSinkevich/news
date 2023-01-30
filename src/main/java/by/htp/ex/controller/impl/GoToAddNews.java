package by.htp.ex.controller.impl;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAddNews implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("presentation", "addNews");

        request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
    }
}
