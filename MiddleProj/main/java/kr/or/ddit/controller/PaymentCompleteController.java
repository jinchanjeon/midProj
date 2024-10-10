package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/payment/complete")
public class PaymentCompleteController extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String imp_uid = request.getParameter("imp_uid");
	    
	    PaymentService paymentService = new PaymentService();
	    try {
	        // 결제 검증
	        boolean isVerified = paymentService.verifyPayment(imp_uid);
	        if (isVerified) {
	            // 결제 정보 DB 저장
	            response.setStatus(HttpServletResponse.SC_OK);
	            response.getWriter().write("결제 완료 처리 성공");
	        } else {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            response.getWriter().write("결제 확인 실패");
	        }
	    } catch (Exception e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("결제 확인 중 오류 발생");
	    }
	}

}
