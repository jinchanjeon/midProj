package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.ManagerMovieScheduleService;
import kr.or.ddit.service.ManagerMovieScheduleServiceImpl;
import kr.or.ddit.vo.MovieScheduleVO;

@WebServlet("/schedule/*")
public class MovieScheduleController extends HttpServlet {

    private ManagerMovieScheduleService scheduleService = ManagerMovieScheduleServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        
        if (action.equals("/add")) {
            MovieScheduleVO schedule = new MovieScheduleVO();
            schedule.setScheduleId(request.getParameter("scheduleId"));
            schedule.setMovieId(request.getParameter("movieId"));
            schedule.setScreeningDate(request.getParameter("screeningDate"));
            schedule.setMovieStart(request.getParameter("movieStart"));
            schedule.setMovieEnd(request.getParameter("movieEnd"));
            schedule.setRoomNum(request.getParameter("roomNum"));

            scheduleService.addSchedule(schedule);
            response.sendRedirect(request.getContextPath() + "/schedule/list");
        } else if (action.equals("/update")) {
            MovieScheduleVO schedule = new MovieScheduleVO();
            schedule.setScheduleId(request.getParameter("scheduleId"));
            schedule.setMovieId(request.getParameter("movieId"));
            schedule.setScreeningDate(request.getParameter("screeningDate"));
            schedule.setMovieStart(request.getParameter("movieStart"));
            schedule.setMovieEnd(request.getParameter("movieEnd"));
            schedule.setRoomNum(request.getParameter("roomNum"));

            scheduleService.updateSchedule(schedule);
            response.sendRedirect(request.getContextPath() + "/schedule/list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        
        if (action.equals("/delete")) {
            String scheduleId = request.getParameter("scheduleId");
            scheduleService.deleteSchedule(scheduleId);
            response.sendRedirect(request.getContextPath() + "/schedule/list");
        } else if (action.equals("/list")) {
            List<MovieScheduleVO> schedules = scheduleService.getAllSchedules();
            request.setAttribute("schedules", schedules);
            request.getRequestDispatcher("/listMovieSchedules.jsp").forward(request, response);
        }
    }
}
