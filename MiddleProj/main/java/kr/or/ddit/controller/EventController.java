package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.EventServiceImpl;
import kr.or.ddit.service.iEventService;
import kr.or.ddit.vo.EventVo;

@WebServlet("/eventList")
public class EventController extends HttpServlet {
	
	iEventService eventService = EventServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<EventVo> eventList = eventService.eventList();
		System.out.println(eventService.eventList());
		
		req.setAttribute("eventList", eventList); 
		
		req.getRequestDispatcher("/WEB-INF/view/EventUI.jsp").forward(req, resp);
	}

}