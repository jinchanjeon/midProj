package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.MypurchaseDaoImpl;
import kr.or.ddit.dao.iMypurchaseDao;
import kr.or.ddit.vo.MypurchaseVo;

public class MypurchaseServiceImpl implements iMypurchaseService {

	private static MypurchaseServiceImpl instance;

	private MypurchaseServiceImpl() {

	}

	public static MypurchaseServiceImpl getInstance() {
		if (instance == null) {
			instance = new MypurchaseServiceImpl();

		}
		return instance;
	}
	
	iMypurchaseDao mypurchaseDao = MypurchaseDaoImpl.getInstance();

	@Override
	public List<MypurchaseVo> getmy(int memNo) {
		return mypurchaseDao.getmy(memNo);
	}
}
