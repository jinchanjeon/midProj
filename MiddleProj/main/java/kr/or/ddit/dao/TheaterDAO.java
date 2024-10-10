package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.vo.TheaterVo;

public class TheaterDAO {
    // MyBatis SqlSessionFactory를 주입받는 방법을 설정하세요
    private SqlSessionFactory sqlSessionFactory;

    public TheaterDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<TheaterVo> getTheatersByRegion(String regNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TheaterMapper mapper = sqlSession.getMapper(TheaterMapper.class);
            return mapper.getTheatersByRegion(regNo);
        }
    }
}