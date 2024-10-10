package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.ProdCartVo;
import kr.or.ddit.vo.ProdOrderVo;
import kr.or.ddit.vo.ProdPayVo;
import kr.or.ddit.vo.ProdVo;

public interface iPaymentService {

	/**
	 * 해당 상품 번호에 대한 상품 조회
	 * @param prodNo
	 * @return
	 */
	public ProdVo selectProdList(int prodNo);
	
	
	/**
	 * 주문 정보 삽입
	 * @param order
	 */
	public void insertOrder(ProdOrderVo order);
	
	
	/**
	 * 결제 내역 추가
	 * @param pay
	 */
	public void insertPayment(ProdPayVo pay);
	
	
	/**
	 * 장바구니 상품 조회
	 * @param memNo
	 * @return
	 */
	public List<ProdCartVo> selectCartItems(int memNo);
	
	
	/**
	 * 특정 카테고리에 속한 상품 조회
	 * @param cateCodeNo
	 * @return
	 */
	public List<ProdVo> selectProdByCategory(int cateCodeNo);
}
