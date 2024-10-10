package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.MemberDaoImpl;
import kr.or.ddit.dao.SpotDaoImpl;
import kr.or.ddit.dao.iMemberDao;
import kr.or.ddit.dao.iSpotDao;
import kr.or.ddit.mybatis.MyBatisDao;
import kr.or.ddit.vo.SpotVo;

public class SpotServiceImpl implements iSpotService{
	private static SpotServiceImpl instance;

	private SpotServiceImpl() {

	}

	public static SpotServiceImpl getInstance() {
		if (instance == null) {
			instance = new SpotServiceImpl();

		}
		return instance;
	}

	iSpotDao spotDao = SpotDaoImpl.getInstance();

	
	@Override
	public List<SpotVo> selSpot() {
		return spotDao.selSpot();
	}
}
