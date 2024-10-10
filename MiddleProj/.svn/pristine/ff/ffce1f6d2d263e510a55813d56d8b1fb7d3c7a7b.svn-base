package kr.or.ddit.controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.ReviewServiceImpl;
import kr.or.ddit.service.iReviewService;
import kr.or.ddit.vo.ReviewVo;

@WebServlet("/insertReview.do")
public class ReviewInsertController extends HttpServlet {
    iReviewService reviewService = ReviewServiceImpl.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int memNo= Integer.parseInt(req.getParameter("memNo"));
        String movieNo = req.getParameter("movieNo");
        String reviewContent = req.getParameter("reviewContent");
        int reviewStar = Integer.parseInt(req.getParameter("reviewStar"));

        ReviewVo review = new ReviewVo();
        review.setMovieNo(movieNo);
        review.setReviewContent(reviewContent);
        review.setMemNo(memNo); 
        review.setReviewStar(reviewStar);
        System.out.println("memNo: " + memNo); // 확인용
        System.out.println("movieNo: " + movieNo); // 확인용
        System.out.println("reviewContent: " + reviewContent); // 확인용
        System.out.println("reviewStar: " + reviewStar); // 확인용

        reviewService.insertReview(review);
        resp.sendRedirect(req.getContextPath() + "/movieDetail.do?movie_no=" + movieNo); // 상세 페이지로 리다이렉트
    }

}