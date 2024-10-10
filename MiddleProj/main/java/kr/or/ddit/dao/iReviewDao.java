package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.MemberReview;
import kr.or.ddit.vo.ReviewVo;

public interface iReviewDao{
	public List<ReviewVo> reviewList(String movie);
	public void insertReview(ReviewVo review);
	public void updateReview(ReviewVo review);
	public void deleteReview(String reviewCode);
	public ReviewVo selectToUpdateReview(ReviewVo review);
	public ReviewVo selectReview(String reviewCode);
	
	//회원 리뷰 조회
	public List<MemberReview> myreview(int memNo);
	public Float reviewAVG(String movieNo);
}