package kr.or.ddit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.type.TypeReference;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dao.SeatDAO;
import kr.or.ddit.vo.SeatVo;

@WebServlet("/reserveSeats.do")
public class ReserveSeatsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // JSON 데이터 읽기
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        
        // JSON 데이터 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        List<SeatVo> selectedSeats = objectMapper.readValue(sb.toString(), new TypeReference<List<SeatVo>>() {});

        // 데이터베이스에 예약 정보 저장
        SeatDAO seatDAO = new SeatDAO();
        for (SeatVo seat : selectedSeats) {
            seatDAO.reserveSeat(seat); // 좌석 예약 처리 메서드 호출
        }

        // 응답 설정
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("예약 성공");
    }
}