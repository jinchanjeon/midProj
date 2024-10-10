package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.dao.SeatDAO;
import kr.or.ddit.mybatis.MybatisUtil;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.SeatVo;

@WebServlet("/getSeats.do")
public class SeatServlet extends HttpServlet {
	private SeatDAO seatDAO;

	public void init() {
		seatDAO = new SeatDAO(MybatisUtil.getSqlSessionFactory());
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
	        Integer memNo = (Integer) session.getAttribute("mem_no"); 
	        System.out.println("Session memNo: " + memNo);


	        if (memNo == null) {
	            response.sendRedirect(request.getContextPath() + "/login.do");  
	            return;
	        }

		// 파라미터 받기
		String regNo = request.getParameter("regNo");
		String regName = request.getParameter("regName");
		String spotCode = request.getParameter("spotCode");
		String date = request.getParameter("date");
		String scheduleNo = request.getParameter("scheduleNo");
		String movieNo = request.getParameter("movieNo"); // movieNo 추가
		String movieName = request.getParameter("movieName"); // movieNo 추가
//		String movieStartTime = request.getParameter("movieStartTime"); // movieNo 추가
//		String movieEndTime = request.getParameter("movieEndTime"); // movieNo 추가
		String spotName = request.getParameter("spotName"); // movieNo 추가
		
	

		// 값이 잘 들어왔는지 출력


		// 디버깅용 출력
		System.out.println("regNo: " + regNo);
		System.out.println("regName: " + regName);
		System.out.println("spotCode: " + spotCode);
		System.out.println("date: " + date);
		System.out.println("scheduleNo: " + scheduleNo);
		System.out.println("movieNo: " + movieNo); // movieNo 확인
		System.out.println("movieName: " + movieName); // movieNo 확인
//		System.out.println("movieStartTime: " + movieStartTime); // movieNo 확인
//		System.out.println("movieEndTime: " + movieEndTime); // movieNo 확인
		System.out.println("spotName: " + spotName); // movieNo 확인

		// 좌석 정보 가져오기
		List<SeatVo> seatList = seatDAO.getAvailableSeats(scheduleNo);

		// 결과를 JSP로 전달
		request.setAttribute("seatList", seatList);
		request.setAttribute("movieNo", movieNo); // movieNo 전달
		request.setAttribute("movieName", movieName); // movieNo 전달
		request.setAttribute("date", date);
		request.setAttribute("spotCode", spotCode);
		request.setAttribute("scheduleNo", scheduleNo);
		request.setAttribute("regName", regName);
//		request.setAttribute("movieStartTime", movieStartTime);
//		request.setAttribute("movieEndTime", movieEndTime);
		request.setAttribute("spotName", spotName);
		
		// JSP로 포워딩
		request.getRequestDispatcher("/WEB-INF/view/selectSeats.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String movieStartTime = request.getParameter("movieStartTime");
	    String movieEndTime = request.getParameter("movieEndTime");

	    System.out.println("Movie Start Time: " + movieStartTime);
	    System.out.println("Movie End Time: " + movieEndTime);
	}

}