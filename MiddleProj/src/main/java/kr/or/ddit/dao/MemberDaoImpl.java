package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisDao;
import kr.or.ddit.vo.ManageVo;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.SpotVo;

public class MemberDaoImpl  extends MyBatisDao implements iMemberDao{

	private static MemberDaoImpl instance;

	private MemberDaoImpl() {

	}

	public static MemberDaoImpl getInstance() {
		if (instance == null) {
			instance = new MemberDaoImpl();

		}
		return instance;
	}

	@Override
	public MemberVo login(MemberVo member) {

		return selectOne("member.login", member);
	}

	@Override
	public int join(MemberVo member) {

		return insert("member.join",member);
	}

	@Override
	public MemberVo findId(MemberVo memberVo) {
		return selectOne("member.findId", memberVo);
	}

	@Override
	public int selectMemNo(String memId) {
		return selectOne("member.selectMemNo",memId);
	}

	@Override
	public MemberVo findPw(MemberVo memberVo) {
		
		return selectOne("member.findPw", memberVo);
	}

	@Override
	public int update(MemberVo member) {
		return update("member.update",member);
	}

	@Override
	public int withdraw(MemberVo member) {
		return update("member.withdraw",member);
	}

	@Override
	public List<MemberVo> selectMem() {
		return selectList("member.selectMem");
	}

	@Override
	public MemberVo selectMembyId(String id) {
		return selectOne("member.selectMembyId",id);
	}

	@Override
	public void insertManager(MemberVo member) {
		insert("member.insertManager",member);
	}

	@Override
	public void updateManager(MemberVo member) {
		update("member.updateManager",member);
	}

	@Override
	public MemberVo selectByNo(int memNo) {
		return selectOne("member.selectByNo",memNo);
	}

	@Override
	public void manager(ManageVo manager) {
		insert("member.manager",manager);
	}

	@Override
	public void updateMng(ManageVo manage) {
		update("member.updateMng", manage);
	}

	@Override
	public ManageVo selMng(int memNo) {
		return selectOne("member.selMng", memNo);
	}

	@Override
	public List<MemberVo> selSpotMember() {
		return selectList("member.selSpotMember");
	}
	
	@Override
	public int pwUpdate(String memId) {
		return update("member.pwUpdate", memId);
	}

	
}

