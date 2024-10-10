package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.MyticketDaoImpl;
import kr.or.ddit.dao.iMyticketDao;
import kr.or.ddit.vo.MyticketVo;

public class MyticketServiceImpl implements iMyticketService {

	private static MyticketServiceImpl instance;

	private MyticketServiceImpl() {

	}

	public static MyticketServiceImpl getInstance() {
		if (instance == null) {
			instance = new MyticketServiceImpl();

		}
		return instance;
	}

	iMyticketDao myticketDao = MyticketDaoImpl.getInstance();
	@Override
	public List<MyticketVo> myticket(int memNo) {
		return myticketDao.myticket(memNo);
	}
}
