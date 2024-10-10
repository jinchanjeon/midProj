package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.ReviewDaoImpl;
import kr.or.ddit.dao.iReviewDao;
import kr.or.ddit.vo.MemberReview;
import kr.or.ddit.vo.ReviewVo;

public class ReviewServiceImpl implements iReviewService{

	private static ReviewServiceImpl instance;

	private ReviewServiceImpl() {

	}

	public static ReviewServiceImpl getInstance() {
		if (instance == null) {
			instance = new ReviewServiceImpl();

		}
		return instance;
	}

	iReviewDao reviewDao = ReviewDaoImpl.getInstance();

	@Override
	public List<ReviewVo> reviewList(String movie) {
		return reviewDao.reviewList(movie);
	}

	@Override
	public void insertReview(ReviewVo review) {
		reviewDao.insertReview(review);
	}

	@Override
	public void updateReview(ReviewVo review) {
		reviewDao.updateReview(review);
	}

	@Override
	public void deleteReview(String reviewCode) {
		reviewDao.deleteReview(reviewCode);
	}

	@Override
	public ReviewVo selectToUpdateReview(ReviewVo review) {
		return reviewDao.selectToUpdateReview(review);
	}

	@Override
	public ReviewVo selectReview(String reviewCode) {
		return reviewDao.selectReview(reviewCode);
	}

	@Override
	public List<MemberReview> myreview(int memNo) {
		return reviewDao.myreview(memNo);
	}

	@Override
	public Float reviewAVG(String movieNo) {
		return reviewDao.reviewAVG(movieNo);
	}

}