package kr.or.ddit.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kr.or.ddit.vo.SeatVo;

public class SeatDAO {
    private SqlSessionFactory sqlSessionFactory;

    public SeatDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<SeatVo> getAllSeats() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SeatMapper mapper = sqlSession.getMapper(SeatMapper.class);
            return mapper.getAllSeats();
        }
    }

    public SeatVo getSeatByNo(String seatNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SeatMapper mapper = sqlSession.getMapper(SeatMapper.class);
            return mapper.getSeatByNo(seatNo);
        }
    }

    public void reserveSeat(SeatVo seat) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SeatMapper mapper = sqlSession.getMapper(SeatMapper.class);
            mapper.reserveSeat(seat);
            sqlSession.commit();
        }
    }

    public void cancelReservation(String seatNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SeatMapper mapper = sqlSession.getMapper(SeatMapper.class);
            mapper.cancelReservation(seatNo);
            sqlSession.commit();
        }
    }
}
