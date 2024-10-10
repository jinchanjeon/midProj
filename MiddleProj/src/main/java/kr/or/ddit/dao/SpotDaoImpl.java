package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisDao;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.vo.SpotVo;

public class SpotDaoImpl extends MyBatisDao implements iSpotDao{


	private static SpotDaoImpl instance;

	private SpotDaoImpl() {

	}

	public static SpotDaoImpl getInstance() {
		if (instance == null) {
			instance = new SpotDaoImpl();

		}
		return instance;
	}

	@Override
	public List<SpotVo> selSpot() {
		return selectList("member.selSpot");
	}

	
}
