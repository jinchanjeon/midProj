package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.StoreVo;

public interface iStoreService {
    List<StoreVo> getAllProducts();
    StoreVo getProductById(String productId);
    void addProduct(StoreVo product);
    void updateProduct(StoreVo product);
    void deleteProduct(String productId);
    
 // 장바구니 관련 메서드
    List<StoreVo> getCartItems(int memNo); 
    boolean addToCart(StoreVo cartItem);
    boolean updateCartItemQuantity(int cartId, int quantity);
    boolean removeFromCart(int cartId);
    List<StoreVo> getProductsByCategory(String categoryId);
	List<Map<String, String>> getAllCategories();

}