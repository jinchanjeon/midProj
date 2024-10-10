package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.MypurchaseVo;

public interface iMypurchaseDao {

	//결제내역 불러오기
	List<MypurchaseVo> getmy(int memNo);

}
