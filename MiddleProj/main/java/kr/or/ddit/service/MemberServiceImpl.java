package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.MemberDaoImpl;
import kr.or.ddit.dao.iMemberDao;
import kr.or.ddit.vo.ManageVo;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.SpotVo;

public class MemberServiceImpl implements iMemberService{

	private static MemberServiceImpl instance;

	private MemberServiceImpl() {

	}

	public static MemberServiceImpl getInstance() {
		if (instance == null) {
			instance = new MemberServiceImpl();

		}
		return instance;
	}

	iMemberDao memberDao = MemberDaoImpl.getInstance();

	@Override
	public MemberVo login(MemberVo member) {
		return memberDao.login(member);
	}

	@Override
	public int join(MemberVo member) {

		return memberDao.join(member);
	}

	@Override
	public MemberVo findId(MemberVo memberVo) {
		return memberDao.findId(memberVo);
	}

	@Override
	public int selectMemNo(String memId) {
		return memberDao.selectMemNo(memId);
	}

	@Override
	public MemberVo findPw(MemberVo memberVo) {
		return memberDao.findPw(memberVo);
	}

	@Override
	public int update(MemberVo member) {
		return memberDao.update(member);
	}

	@Override
	public List<MemberVo> selectMem() {
		return memberDao.selectMem();
	}


	@Override
	public int withdraw(MemberVo member) {

		return memberDao.withdraw(member);
		
	}

	@Override
	public MemberVo selectMembyId(String id) {
		return memberDao.selectMembyId(id);
	}

	@Override
	public void insertManager(MemberVo member) {
		memberDao.insertManager(member);
	}

	@Override
	public void updateManager(MemberVo member) {
		memberDao.updateManager(member);
	}

	@Override
	public MemberVo selectByNo(int memNo) {
		return memberDao.selectByNo(memNo);
	}

	@Override
	public void manager(ManageVo manager) {
		memberDao.manager(manager);
	}

	@Override
	public void updateMng(ManageVo manage) {
		memberDao.updateMng(manage);
	}

	@Override
	public ManageVo selMng(int memNo) {
		return memberDao.selMng(memNo);
	}

	@Override
	public List<MemberVo> selSpotMember() {
		return memberDao.selSpotMember();
	}
	
	@Override
	public int pwUpdate(String memId) {
		return memberDao.pwUpdate(memId);
	}

	

}
