package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.MyticketVo;

public interface iMyticketDao {

	List<MyticketVo> myticket(int memNo);

}
