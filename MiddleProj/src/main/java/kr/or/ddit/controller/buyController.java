package kr.or.ddit.controller;

import java.io.IOException;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.service.PaymentServiceImpl;
import kr.or.ddit.service.StoreServiceImpl;
import kr.or.ddit.service.iMemberService;
import kr.or.ddit.service.iPaymentService;
import kr.or.ddit.service.iStoreService;
import kr.or.ddit.vo.ProdOrderVo;
import kr.or.ddit.vo.ProdPayVo;
import kr.or.ddit.vo.PurchaseVo;

@WebServlet("/buy.do")
public class buyController extends HttpServlet {

	iMemberService memberService = MemberServiceImpl.getInstance();
	iPaymentService payService = PaymentServiceImpl.getInstance();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		try {
			String memberId = req.getParameter("memberId");
			int memNo = memberService.selectMemNo(memberId);
			String merchantUid = req.getParameter("merchant_uid");
			String itemsJson = req.getParameter("items"); // JSON 형태로 전달된 상품 정보
			int totalAmount = Integer.parseInt(req.getParameter("totalAmount"));
			// JSON 파싱
			JSONArray items = new JSONArray(itemsJson);
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

			
			for (int i = 0; i < items.length(); i++) {
				JSONObject item = items.getJSONObject(i);
				String productId = item.getString("productId");
				int quantity = item.getInt("quantity");
				int amount = item.getInt("amount");
				
				ProdOrderVo orderVo = new ProdOrderVo();
//				orderVo.setOrderNo(merchantUid);
				orderVo.setMemNo(memNo);
				orderVo.setOrderPrice(amount);
				orderVo.setOrderCnt(quantity);
				orderVo.setProductId(productId);
				payService.insertOrder(orderVo);
				
			}
			
			ProdPayVo payVo = new ProdPayVo(); 
			 payVo.setProdPayPrice(totalAmount);
			 payVo.setProdPayCreated(currentTimestamp);
			 payVo.setOrderNo(merchantUid);
			 payService.insertPayment(payVo);
			 
			
			

			// Success response
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().write("{\"message\":\"주문이 성공적으로 저장되었습니다.\"}");
		} catch (Exception e) {
			e.printStackTrace(); // 로그에 예외를 기록
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().write("{\"error\":\"서버 오류가 발생했습니다.\"}");
		}
	}

}
