package kr.or.ddit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kr.or.ddit.vo.SeatVo;
import kr.or.ddit.dao.SeatMapper; // SeatMapper를 import합니다.

public class SeatDAO {
    private SqlSessionFactory sqlSessionFactory;

    public SeatDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // 스케줄 번호에 해당하는 좌석 정보를 가져오는 메서드
    public List<SeatVo> getAvailableSeats(String scheduleNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SeatMapper mapper = sqlSession.getMapper(SeatMapper.class);
            return mapper.getAvailableSeats(scheduleNo); // 새로운 메서드 사용
        }
    }
    public void reserveSeat(SeatVo seat) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = getConnection(); // DB 연결
            String sql = "UPDATE RESERVED_SEAT SET SOLDOUT = 1 WHERE SEAT_NO = ?"; // SOLDOUT 상태 업데이트 쿼리
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seat.getSeatNo());
            pstmt.executeUpdate(); // 쿼리 실행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, conn); // 리소스 닫기
        }
    }
}
