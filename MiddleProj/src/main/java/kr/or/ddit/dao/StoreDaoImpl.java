package kr.or.ddit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mybatis.MybatisUtil;
import kr.or.ddit.vo.SpotVo;
import kr.or.ddit.vo.StoreVo;

public class StoreDaoImpl implements iStoreDao {
	
	private static StoreDaoImpl instance;

	private StoreDaoImpl() {

	}

	public static StoreDaoImpl getInstance() {
		if (instance == null) {
			instance = new StoreDaoImpl();

		}
		return instance;
	}


    
    @Override
    public List<StoreVo> getAllProducts() {
        System.out.println("StoreDaoImpl: getAllProducts 메서드 호출");
        try (SqlSession sqlSession = MybatisUtil.getInstance()) {
            List<StoreVo> products = sqlSession.selectList("store.getAllProducts");
            System.out.println("StoreDaoImpl: 조회된 제품 수: " + (products != null ? products.size() : "null"));
            if (products != null && !products.isEmpty()) {
                System.out.println("StoreDaoImpl: 첫 번째 제품 정보: " + products.get(0));
            }
            return products;
        }
    }
    
    @Override
    public StoreVo getProductById(String productId) {
        try (SqlSession sqlSession = MybatisUtil.getInstance()) {
            return sqlSession.selectOne("store.getProductById", productId);
        }
    }

    @Override
    public void addProduct(StoreVo product) {
        try (SqlSession sqlSession = MybatisUtil.getInstance()) {
            sqlSession.insert("store.addProduct", product);
            sqlSession.commit();
        }
    }

    @Override
    public void updateProduct(StoreVo product) {
        try (SqlSession sqlSession = MybatisUtil.getInstance()) {
            sqlSession.update("store.updateProduct", product);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteProduct(String productId) {
        try (SqlSession sqlSession = MybatisUtil.getInstance()) {
            sqlSession.delete("store.deleteProduct", productId);
            sqlSession.commit();
        }
    }
    
    // 장바구니
    @Override
    public List<StoreVo> getCartItems(int memNo) {
        System.out.println("StoreDaoImpl: getCartItems 메서드 호출");
        try (SqlSession sqlSession = MybatisUtil.getInstance()) {
            List<StoreVo> cartItems = sqlSession.selectList("store.getCartItems", memNo);
            System.out.println("StoreDaoImpl: 조회된 장바구니 항목 수: " + (cartItems != null ? cartItems.size() : "null"));
            return cartItems;
        }
    }

    @Override
    public int addToCart(StoreVo cartItem) {
        System.out.println("StoreDaoImpl: addToCart 메서드 호출");
        try (SqlSession sqlSession = MybatisUtil.getInstance()) {
            int result = sqlSession.insert("store.addToCart", cartItem);
            sqlSession.commit();
            return result;
        }
    }

    @Override
    public int updateCartItemQuantity(int cartId, int quantity) {
        System.out.println("StoreDaoImpl: updateCartItemQuantity 메서드 호출");
        try (SqlSession sqlSession = MybatisUtil.getInstance()) {
            StoreVo cartItem = new StoreVo();
            cartItem.setCart_id(cartId);
            cartItem.setQuantity(quantity);
            int result = sqlSession.update("store.updateCartItemQuantity", cartItem);
            sqlSession.commit();
            return result;
        }
    }

    @Override
    public int removeFromCart(int cartId) {
        System.out.println("StoreDaoImpl: removeFromCart 메서드 호출");
        try (SqlSession sqlSession = MybatisUtil.getInstance()) {
            int result = sqlSession.delete("store.removeFromCart", cartId);
            sqlSession.commit();
            return result;
        }
    }
    
    @Override
    public List<StoreVo> getProductsByCategory(String categoryId) {
        System.out.println("StoreDaoImpl: getProductsByCategory 메서드 호출");
        try (SqlSession sqlSession = MybatisUtil.getInstance()) {
            List<StoreVo> products = sqlSession.selectList("store.getProductsByCategory", categoryId);
            System.out.println("StoreDaoImpl: 조회된 제품 수: " + (products != null ? products.size() : "null"));
            return products;
        }
    }

    @Override
    public List<Map<String, String>> getAllCategories() {
        System.out.println("StoreDaoImpl: getAllCategories 메서드 호출");
        try (SqlSession sqlSession = MybatisUtil.getInstance()) {
            List<Map<String, String>> categories = sqlSession.selectList("store.getAllCategories");
            System.out.println("StoreDaoImpl: 조회된 카테고리 수: " + (categories != null ? categories.size() : "null"));
            return categories;
        }
    }
}