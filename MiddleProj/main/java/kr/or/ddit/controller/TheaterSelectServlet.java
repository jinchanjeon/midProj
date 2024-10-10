package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dao.MovieDaoImpl;
import kr.or.ddit.dao.ScheduleDAO;
import kr.or.ddit.dao.iMovieDao;
import kr.or.ddit.mybatis.MybatisUtil;
import kr.or.ddit.vo.MovieVo;
import kr.or.ddit.vo.ScheduleVo;

@WebServlet("/theaterSelect.do")
public class TheaterSelectServlet extends HttpServlet { 
    private MovieDaoImpl movieDAO; // MovieDaoImpl 추가
    private ScheduleDAO scheduleDAO;
    private SqlSessionFactory sqlSessionFactory; // SqlSessionFactory 필드 추가

    @Override
    public void init() throws ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String movieNo = request.getParameter("movieNo");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/theaterSelect.jsp");
        dispatcher.forward(request, response); // JSP로 포워딩
    }
}
