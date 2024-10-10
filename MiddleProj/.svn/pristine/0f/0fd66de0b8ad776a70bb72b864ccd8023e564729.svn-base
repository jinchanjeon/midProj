package kr.or.ddit.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.movie.service.MemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.service.iMemberService;
import kr.or.ddit.vo.ManageVo;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.ReviewVo;

@WebServlet("/getManager.do")
public class ManagerController extends HttpServlet {

	iMemberService memberService = MemberServiceImpl.getInstance();
	
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        int memNo = Integer.parseInt(req.getParameter("memNo"));
	        MemberVo manager = memberService.selectByNo(memNo);
	        // JSON 응답 설정
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");

	        // Gson을 사용하여 JSON으로 변환
	        Gson gson = new Gson();
	        String json = gson.toJson(manager);


	        resp.getWriter().write(json); // JSON 데이터 전송
	    }
}
