package kr.or.ddit.movie.main;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.movie.service.InventoryService;
import kr.or.ddit.movie.service.MemberService;
import kr.or.ddit.movie.service.MovieService;
import kr.or.ddit.movie.service.SalesService;

@WebServlet("/manager.do")
public class MainController extends HttpServlet {
    private MemberService memberService = new MemberService();
    private MovieService movieService = new MovieService();
    private InventoryService inventoryService = new InventoryService();
    private SalesService salesService = new SalesService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("inventory".equals(action)) {
            request.setAttribute("inventories", inventoryService.getAllInventories());
            RequestDispatcher dispatcher = request.getRequestDispatcher("inventory.jsp");
            dispatcher.forward(request, response);
        } else if ("movies".equals(action)) {
            request.setAttribute("movies", movieService.getAllMovies());
            RequestDispatcher dispatcher = request.getRequestDispatcher("movie.jsp");
            dispatcher.forward(request, response);
        } else if ("sales".equals(action)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("sales.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("inquiry.jsp");
            dispatcher.forward(request, response);
        }
    }
}
