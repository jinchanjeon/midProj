package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisDao;
import kr.or.ddit.vo.MemberReview;
import kr.or.ddit.vo.ReviewVo;

public class ReviewDaoImpl extends MyBatisDao implements iReviewDao{

	private static ReviewDaoImpl instance;

	private ReviewDaoImpl() {

	}

	public static ReviewDaoImpl getInstance() {
		if (instance == null) {
			instance = new ReviewDaoImpl();

		}
		return instance;
	}

	
	@Override
	public List<ReviewVo> reviewList(String movie) {
		return selectList("review.reviewList", movie);
	}

	@Override
	public void insertReview(ReviewVo review) {
		insert("review.insertReview",review);
	}

	@Override
	public void updateReview(ReviewVo review) {
		update("review.updateReview",review);
	}

	@Override
	public void deleteReview(String reviewCode) {
		update("review.deleteReview",reviewCode);
	}

	@Override
	public ReviewVo selectToUpdateReview(ReviewVo review) {
		return selectOne("review.selectToUpdateReview",review);
	}

	@Override
	public ReviewVo selectReview(String reviewCode) {
		return selectOne("review.selectReview",reviewCode);
	}

	@Override
	public List<MemberReview> myreview(int memNo) {
		return selectList("memberReview.myreview", memNo);
	}

	@Override
	public Float reviewAVG(String movieNo) {
		return selectOne("review.reviewAVG",movieNo);
	}

}