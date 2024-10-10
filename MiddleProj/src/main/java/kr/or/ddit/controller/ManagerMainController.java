package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class ManagerMainController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");

		if (type == null) {
			// 기본 대시보드 페이지로 포워딩
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
			dispatcher.forward(request, response);
			return;
		}

		/*
		 * RequestDispatcher dispatcher = null;
		 * 
		 * if ("movieSchedule".equals(type)) { dispatcher =
		 * request.getRequestDispatcher("/movieSchedule.jsp"); } else if
		 * ("product".equals(type)) { dispatcher =
		 * request.getRequestDispatcher("/product.jsp"); } else if
		 * ("sales".equals(type)) { dispatcher =
		 * request.getRequestDispatcher("/sales.jsp"); } else {
		 * response.sendError(HttpServletResponse.SC_NOT_FOUND,
		 * "The requested resource is not available."); return; }
		 * 
		 * if (dispatcher != null) { dispatcher.forward(request, response); return; }
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
