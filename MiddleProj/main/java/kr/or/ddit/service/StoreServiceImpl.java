package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.StoreDaoImpl;
import kr.or.ddit.dao.iStoreDao;
import kr.or.ddit.vo.StoreVo;

public class StoreServiceImpl implements iStoreService {
    

	private static StoreServiceImpl instance;

	private StoreServiceImpl() {

	}

	public static StoreServiceImpl getInstance() {
		if (instance == null) {
			instance = new StoreServiceImpl();

		}
		return instance;
	}

    
	iStoreDao storeDao = StoreDaoImpl.getInstance();
	
    @Override
    public List<StoreVo> getAllProducts() {
        return storeDao.getAllProducts();
    }

    @Override
    public StoreVo getProductById(String productId) {
        return storeDao.getProductById(productId);
    }

    @Override
    public void addProduct(StoreVo product) {
        storeDao.addProduct(product);
    }

    @Override
    public void updateProduct(StoreVo product) {
        storeDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(String productId) {
        storeDao.deleteProduct(productId);
    }
    
    // 장바구니 관련 메서드 구현
    @Override
    public List<StoreVo> getCartItems(int memNo) {
        return storeDao.getCartItems(memNo);
    }

    @Override
    public boolean addToCart(StoreVo cartItem) {
        try {
            storeDao.addToCart(cartItem);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCartItemQuantity(int cartId, int quantity) {
        try {
            storeDao.updateCartItemQuantity(cartId, quantity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeFromCart(int cartId) {
        try {
            storeDao.removeFromCart(cartId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}