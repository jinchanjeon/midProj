package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dao.TheaterDAO;
import kr.or.ddit.mybatis.MybatisUtil;
import kr.or.ddit.vo.TheaterVo;

@WebServlet("/getTheatersByRegion.do")
public class GetTheatersByRegionServlet extends HttpServlet {
    private TheaterDAO theaterDAO;

    @Override
    public void init() throws ServletException {
    	theaterDAO = new TheaterDAO(MybatisUtil.getSqlSessionFactory());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regNo = request.getParameter("regNo");
        List<TheaterVo> theaters = theaterDAO.getTheatersByRegion(regNo);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // JSON 형식으로 변환하여 출력
        Gson gson = new Gson();
        String json = gson.toJson(theaters);
        out.print(json);
        out.flush();
    }
}
