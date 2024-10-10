package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.SalesService;
import kr.or.ddit.service.SalesServiceImpl;
import kr.or.ddit.vo.SalesVO;

@WebServlet("/sales/*")
public class SalesController extends HttpServlet {

    private SalesService salesService = SalesServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action.equals("/add")) {
            SalesVO sale = new SalesVO();
            sale.setSaleId(request.getParameter("saleId"));
            sale.setProductId(request.getParameter("productId"));
            sale.setQuantitySold(Integer.parseInt(request.getParameter("quantitySold")));

            salesService.addSale(sale);
            response.sendRedirect(request.getContextPath() + "/sales/list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action.equals("/delete")) {
            String saleId = request.getParameter("saleId");
            salesService.deleteSale(saleId);
            response.sendRedirect(request.getContextPath() + "/sales/list");
        } else if (action.equals("/list")) {
            List<SalesVO> sales = salesService.getAllSales();
            request.setAttribute("sales", sales);
            request.getRequestDispatcher("/listSales.jsp").forward(request, response);
        }
    }
}
