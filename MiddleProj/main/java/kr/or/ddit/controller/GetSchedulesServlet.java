package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dao.ScheduleMapper;
import kr.or.ddit.mybatis.MybatisUtil;
import kr.or.ddit.vo.ScheduleVo;

@WebServlet("/getSchedules.do")
public class GetSchedulesServlet extends HttpServlet{
	  private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String movieNo = request.getParameter("movieNo");
	        String schDate = request.getParameter("schDate");
	        String spotCode = request.getParameter("spotCode");
	        
	        System.out.println("movieNo: " + movieNo);
	        System.out.println("schDate: " + schDate);
	        System.out.println("spotCode: " + spotCode);


	        SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession();
	        ScheduleMapper scheduleMapper = sqlSession.getMapper(ScheduleMapper.class);

	        List<ScheduleVo> scheduleList = scheduleMapper.getSchedules(movieNo, schDate, spotCode);
	        
	        response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        Gson gson = new Gson();
	        
	        String json = gson.toJson(scheduleList);
	        out.print(json);
	        out.flush();

	        sqlSession.close();
	    }
	}