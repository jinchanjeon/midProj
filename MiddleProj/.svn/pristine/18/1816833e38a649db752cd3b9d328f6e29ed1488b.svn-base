package kr.or.ddit.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import kr.or.ddit.vo.ProdCartVo;
import kr.or.ddit.vo.ProdOrderVo;
import kr.or.ddit.vo.ProdPayVo;
import kr.or.ddit.vo.ProdVo;
import kr.or.ddit.vo.PurchaseVo;

public interface iPaymentDao {

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
	
	boolean verifyPayment(String imp_uid, int amount, String merchant_uid, Timestamp createdTime) throws IOException;

	
	public void insertPurchase(PurchaseVo pur);
	
}
