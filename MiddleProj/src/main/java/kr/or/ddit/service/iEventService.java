package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.EventVo;

public interface iEventService {
	
	public List<EventVo> eventList();
	
	public EventVo eventDetail(String boardNo);
	
	public EventVo eventUpdate(EventVo event);

	public EventVo eventInsert(EventVo event);

	public EventVo eventDelete(EventVo event);
	
	public EventVo eventCnt(String boardNo);
}
