package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.EventServiceImpl;
import kr.or.ddit.service.iEventService;
import kr.or.ddit.vo.EventVo;

@WebServlet("/eventUpdate")
public class EventUpdateController extends HttpServlet{

	iEventService eventService = EventServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String boardNo = req.getParameter("boardNo");
	    
	    System.out.println("글번호 : " + boardNo);
	    EventVo event = eventService.eventDetail(boardNo);
	    req.setAttribute("event", event);
	    System.out.println("선택된 event vo : " + event);
	    req.getRequestDispatcher("/WEB-INF/view/eventUpdate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String title = req.getParameter("title");
	    String content = req.getParameter("content");
	    String boardNo = req.getParameter("boardNo");
	    System.out.println("boardNo: " + boardNo);
	    System.out.println("boardTitle: " + title);
	    System.out.println("boardContent: " + content);
	    
//	    String img = req.getParameter("img");
	    
	    EventVo event = new EventVo();
	    event.setBoardNo(boardNo);
	    event.setBoardTitle(title);
	    event.setBoardContent(content);
	    
	    eventService.eventUpdate(event);


	    
	    resp.sendRedirect(req.getContextPath() + "/eventList");
	}

	
}
