package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.ManageVo;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.SpotVo;

public interface iMemberService  {

	//濡쒓렇??寃利?
	public MemberVo login(MemberVo member);

	//?뚯썝 媛??
	public int join(MemberVo member);

	//?꾩씠??李얘린
	public MemberVo findId(MemberVo memberVo);

	public int selectMemNo(String memId);
	//鍮꾨?踰덊샇 李얘린
	public MemberVo findPw(MemberVo memberVo);

	//媛쒖씤?뺣낫 ?섏젙
	public int update(MemberVo member);
	
	public List<MemberVo> selectMem();


	//?뚯썝 ?덊눜 泥섎━
	public int withdraw(MemberVo member);
	
	public MemberVo selectMembyId(String id);
	public void insertManager(MemberVo member);
	public void updateManager(MemberVo member);
	public MemberVo selectByNo(int memNo);

	public void manager(ManageVo manager);
	public void updateMng(ManageVo manage);
	public ManageVo selMng(int memNo);

	public List<MemberVo> selSpotMember();
	
	//회원 비밀번호 초기화 
	public int pwUpdate(String memId);
}
