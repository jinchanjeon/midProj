package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.service.ReviewServiceImpl;
import kr.or.ddit.service.iMemberService;
import kr.or.ddit.service.iReviewService;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.ReviewVo;

@WebServlet("/updateReview.do")
public class ReviewUpdateController extends HttpServlet {
    iReviewService reviewService = ReviewServiceImpl.getInstance();
    iMemberService memberService = MemberServiceImpl.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 String reviewCode = req.getParameter("reviewCode");
    	 String content = req.getParameter("reviewContent");
    	 String reviewStarStr = req.getParameter("reviewStar");
    	 int reviewStar = Integer.parseInt(reviewStarStr);
    	    
        ReviewVo review = new ReviewVo();
        review.setReviewCode(reviewCode);
        review.setReviewContent(content); 
        review.setReviewStar(reviewStar); 
        reviewService.updateReview(review);
        
        
        System.out.println("수정후: "+review);
        
        resp.sendRedirect(req.getContextPath() + "/movieDetail.do?movie_no=" + req.getParameter("movieNo")); // 삭제 후 상세 페이지로 리다이렉트
    }
}