package kr.or.ddit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.service.StoreServiceImpl;
import kr.or.ddit.service.iStoreService;
import kr.or.ddit.vo.StoreVo;

@WebServlet("/storeCart.do")
public class StoreCartController extends HttpServlet {
    iStoreService storeService = StoreServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StoreCartController: doGet 메소드 호출됨");
        listCartItems(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StoreCartController: doPost 메소드 호출됨");
        String action = request.getParameter("action");
        System.out.println("Action: " + action);

        switch (action) {
            case "add":
                addToCart(request, response);
                break;
            case "remove":
                removeFromCart(request, response);
                break;
            case "update":
                updateCartItem(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 액션");
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StoreCartController: addToCart 메소드 호출됨");
        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        Integer memNo = (Integer) session.getAttribute("mem_no");  // "memNo"에서 "mem_no"로 변경
        String memName = (String) session.getAttribute("mem_name");  // "memName"에서 "mem_name"으로 변경

        System.out.println("Debug - memNo: " + memNo);
        System.out.println("Debug - memName: " + memName);

        if (memNo == null || memName == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"로그인이 필요합니다.\"}");
            return;
        }

        try {
            StoreVo cartItem = new StoreVo();
            cartItem.setProduct_id(productId);
            cartItem.setMem_no(memNo);
            cartItem.setQuantity(quantity);
            cartItem.setMem_name(memName);

            boolean result = storeService.addToCart(cartItem);
            if (result) {
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"success\", \"message\":\"상품이 장바구니에 추가되었습니다.\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"status\":\"error\", \"message\":\"장바구니 추가에 실패했습니다.로그인 후 다시 시도하세요\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"서버 오류가 발생했습니다.\"}");
        }
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StoreCartController: removeFromCart 메소드 호출됨");
        String productId = request.getParameter("productId");
        HttpSession session = request.getSession();
        Integer memNo = (Integer) session.getAttribute("mem_no");

        try {
            // productId와 memNo를 이용해 cartId를 찾습니다.
            List<StoreVo> cartItems = storeService.getCartItems(memNo);
            int cartId = -1;
            for (StoreVo item : cartItems) {
                if (item.getProduct_id().equals(productId)) {
                    cartId = item.getCart_id();
                    break;
                }
            }

            if (cartId == -1) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"status\":\"error\", \"message\":\"장바구니에서 해당 상품을 찾을 수 없습니다.\"}");
                return;
            }

            boolean result = storeService.removeFromCart(cartId);
            if (result) {
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"success\", \"message\":\"상품이 장바구니에서 제거되었습니다.\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"status\":\"error\", \"message\":\"장바구니에서 상품 제거에 실패했습니다.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"서버 오류가 발생했습니다.\"}");
        }
    }

    private void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StoreCartController: updateCartItem 메소드 호출됨");
        String productId = request.getParameter("productId");
        String quantityStr = request.getParameter("quantity");

        System.out.println("Received productId: " + productId);
        System.out.println("Received quantity: " + quantityStr);

        if (productId == null || quantityStr == null || productId.isEmpty() || quantityStr.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"잘못된 요청: productId 또는 quantity가 누락되었습니다.\"}");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            HttpSession session = request.getSession();
            Integer memNo = (Integer) session.getAttribute("mem_no");

            // productId와 memNo를 이용해 cartId를 찾습니다.
            List<StoreVo> cartItems = storeService.getCartItems(memNo);
            int cartId = -1;
            for (StoreVo item : cartItems) {
                if (item.getProduct_id().equals(productId)) {
                    cartId = item.getCart_id();
                    break;
                }
            }

            if (cartId == -1) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"status\":\"error\", \"message\":\"장바구니에서 해당 상품을 찾을 수 없습니다.\"}");
                return;
            }

            boolean result = storeService.updateCartItemQuantity(cartId, quantity);
            if (result) {
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"success\", \"message\":\"장바구니 항목이 업데이트되었습니다.\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"status\":\"error\", \"message\":\"장바구니 항목 업데이트에 실패했습니다.\"}");
            }
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"잘못된 요청: quantity가 유효한 숫자가 아닙니다.\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"서버 오류가 발생했습니다.\"}");
        }
    }

    private void listCartItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StoreCartController: listCartItems 메소드 호출됨");
        HttpSession session = request.getSession();
        Integer memNo = (Integer) session.getAttribute("mem_no"); 

        if (memNo == null) {
            response.sendRedirect(request.getContextPath() + "/login.do");  
            return;
        }

        List<StoreVo> cartItems = storeService.getCartItems(memNo);
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("/WEB-INF/view/storeCart.jsp").forward(request, response);
    }
}