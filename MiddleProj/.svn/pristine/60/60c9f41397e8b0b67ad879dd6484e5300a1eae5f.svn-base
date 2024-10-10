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
import kr.or.ddit.dao.MovieMapper;
import kr.or.ddit.mybatis.MybatisUtil;
import kr.or.ddit.vo.MovieVo;



@WebServlet("/getMovieList.do") // URL 경로 수정 확인
public class GetMovieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession();
        MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class); // MovieMapper 사용
        
        // 영화 목록 가져오기
        List<MovieVo> movieList = movieMapper.getMovieList(); // getMovieList() 호출
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // JSON 형식으로 변환하여 출력
        Gson gson = new Gson();
        String json = gson.toJson(movieList);
        out.print(json);
        out.flush();

        sqlSession.close();
    }
}
