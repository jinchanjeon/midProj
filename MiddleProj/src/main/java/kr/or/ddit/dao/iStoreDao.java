package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.StoreVo;

public interface iStoreDao {
    List<StoreVo> getAllProducts();
    StoreVo getProductById(String productId);
    void addProduct(StoreVo product);
    void updateProduct(StoreVo product);
    void deleteProduct(String productId);
    
    // 장바구니
    List<StoreVo> getCartItems(int memNo);
    int addToCart(StoreVo cartItem);
    int updateCartItemQuantity(int cartId, int quantity);
    int removeFromCart(int cartId);
	List<StoreVo> getProductsByCategory(String categoryId);
	List<Map<String, String>> getAllCategories(); 
	
}