package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dao.SeatDAO;
import kr.or.ddit.mybatis.MybatisUtil;
import kr.or.ddit.vo.SeatVo;

@WebServlet("/getSeats.do") // 단일 서블릿으로 통합
public class SeatServlet extends HttpServlet {
    private SeatDAO seatDAO;

    public void init() {
        seatDAO = new SeatDAO(MybatisUtil.getSqlSessionFactory());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 쿼리 파라미터를 통해 전달된 정보를 가져옵니다.
        String regNo = request.getParameter("regNo");
        String spotCode = request.getParameter("spotCode");
        String date = request.getParameter("date");
        String scheduleNo = request.getParameter("scheduleNo");

        // 좌석 정보를 데이터베이스에서 조회합니다.
        List<SeatVo> seatList = seatDAO.getAllSeats(); // 필요한 경우 여기에 조건 추가

        // 조회한 좌석 정보를 request에 저장합니다.
        request.setAttribute("seatList", seatList);
        request.setAttribute("regNo", regNo);        // 지역 정보
        request.setAttribute("spotCode", spotCode);  // 극장 코드
        request.setAttribute("date", date);          // 날짜
        request.setAttribute("scheduleNo", scheduleNo); // 스케줄 번호

        // selectSeats.jsp로 포워딩합니다.
        request.getRequestDispatcher("/WEB-INF/view/selectSeats.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // POST 요청도 GET으로 처리
    }
}
