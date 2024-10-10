package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.ProductDAO;
import kr.or.ddit.dao.ProductDAOImpl;
import kr.or.ddit.vo.ProductVO;

public class ProductServiceImpl implements ProductService {

    private static ProductServiceImpl instance;
    private ProductDAO productDAO;

    private ProductServiceImpl() {
        productDAO = new ProductDAOImpl();
    }

    public static ProductServiceImpl getInstance() {
        if (instance == null) {
            synchronized (ProductServiceImpl.class) {
                if (instance == null) {
                    instance = new ProductServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void addProduct(ProductVO product) {
        productDAO.insertProduct(product);
    }

    @Override
    public void updateProduct(ProductVO product) {
        productDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(String productId) {
        productDAO.deleteProduct(productId);
    }

    @Override
    public ProductVO getProductById(String productId) {
        return productDAO.selectProductById(productId);
    }

    @Override
    public List<ProductVO> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
