package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisDao;
import kr.or.ddit.vo.MypurchaseVo;

public class MypurchaseDaoImpl extends MyBatisDao implements iMypurchaseDao {

	private static MypurchaseDaoImpl instance;

	private MypurchaseDaoImpl() {

	}

	public static MypurchaseDaoImpl getInstance() {
		if (instance == null) {
			instance = new MypurchaseDaoImpl();

		}
		return instance;
	}

	@Override
	public List<MypurchaseVo> getmy(int memNo) {
		return selectList("mypurchase.getmy", memNo);
	}
}
