package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.EventDaoImpl;
import kr.or.ddit.dao.iEventDao;
import kr.or.ddit.mybatis.MyBatisDao;
import kr.or.ddit.vo.EventVo;

public class EventServiceImpl extends MyBatisDao implements iEventService{


	
	private static EventServiceImpl instance;

	private EventServiceImpl() {

	}

	public static EventServiceImpl getInstance() {
		if (instance == null) {
			instance = new EventServiceImpl();
		}

		return instance;
	}

	private iEventDao eventDao = EventDaoImpl.getInstance();
	
	
	
	
	@Override
	public List<EventVo> eventList() {

		return eventDao.eventList();
	}

	@Override
	public EventVo eventDetail(String boardNo) {
		
		return eventDao.eventDetail(boardNo);
	}

	@Override
	public EventVo eventUpdate(EventVo event) {
		
		return eventDao.eventUpdate(event);
	}


	@Override
	public EventVo eventInsert(EventVo event) {
		return eventDao.eventInsert(event);
	}

	@Override
	public EventVo eventDelete(EventVo event) {
		
		return eventDao.eventDelete(event);
	}

	@Override
	public EventVo eventCnt(String boardNo) {
		
		return eventDao.eventCnt(boardNo);
	}


}
