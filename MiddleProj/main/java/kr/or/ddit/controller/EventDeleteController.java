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

@WebServlet("/eventDelete")
public class EventDeleteController extends HttpServlet{

    iEventService eventService = EventServiceImpl.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String boardNo = req.getParameter("boardNo");
        EventVo event = new EventVo();
        event.setBoardNo(boardNo);
        eventService.eventDelete(event);  // 상태 업데이트

        resp.sendRedirect(req.getContextPath() + "/eventList");
    }

}
