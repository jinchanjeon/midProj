package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kr.or.ddit.vo.ScheduleVo;

public class ScheduleDAO {
	private SqlSessionFactory sqlSessionFactory;

	// 생성자를 통해 SqlSessionFactory 주입받음
	public ScheduleDAO(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	 // movieNo, schDate, spotCode를 인자로 받는 메소드
    public List<ScheduleVo> getSchedules(String movieNo, String schDate, String spotCode) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ScheduleMapper mapper = sqlSession.getMapper(ScheduleMapper.class);
            // spotCode를 매개변수로 전달
            return mapper.getSchedules(movieNo, schDate, spotCode);
		}
	}
}
