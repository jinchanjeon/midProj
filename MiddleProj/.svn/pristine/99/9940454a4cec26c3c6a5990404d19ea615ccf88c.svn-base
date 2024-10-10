package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisDao;
import kr.or.ddit.vo.MyticketVo;

public class MyticketDaoImpl extends MyBatisDao implements iMyticketDao {
	private static MyticketDaoImpl instance;

	private MyticketDaoImpl() {

	}

	public static MyticketDaoImpl getInstance() {
		if (instance == null) {
			instance = new MyticketDaoImpl();

		}
		return instance;
	}

	@Override
	public List<MyticketVo> myticket(int memNo) {
		return selectList("myticket.myticket",memNo);
	}

}
