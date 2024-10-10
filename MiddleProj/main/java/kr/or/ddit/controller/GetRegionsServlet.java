package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.plaf.synth.Region;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dao.RegionDAO;

@WebServlet("/getRegions.do")
public class GetRegionsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Region> regions = RegionDAO.getAllRegions(); // DAO를 통해 지역 목록 가져오기
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        // JSON 형식으로 변환하여 출력
        Gson gson = new Gson();
        String json = gson.toJson(regions);
        out.print(json);
        out.flush();
    }
}
