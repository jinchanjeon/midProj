package kr.or.ddit.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kr.or.ddit.vo.ScheduleInfoVo;

public class ScheduleInfoDAO {
    private SqlSessionFactory sqlSessionFactory;

    public ScheduleInfoDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public ScheduleInfoVo getScheduleInfo(String schNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ScheduleInfoMapper mapper = sqlSession.getMapper(ScheduleInfoMapper.class);
            return mapper.getScheduleInfo(schNo);
        }
    }
}
