package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main/*")
public class ManagerMainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        
        if (action == null || action.equals("/")) {
            request.getRequestDispatcher("/managerMain.jsp").forward(request, response);
        } else if (action.equals("/movieSchedule")) {
            response.sendRedirect(request.getContextPath() + "/schedule/list");
        } else if (action.equals("/product")) {
            response.sendRedirect(request.getContextPath() + "/product/list");
        } else if (action.equals("/sales")) {
            response.sendRedirect(request.getContextPath() + "/sales/list");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
