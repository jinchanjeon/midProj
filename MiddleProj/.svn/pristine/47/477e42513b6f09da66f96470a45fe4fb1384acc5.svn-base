package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.SalesDAO;
import kr.or.ddit.dao.SalesDAOImpl;
import kr.or.ddit.vo.SalesVO;

public class SalesServiceImpl implements SalesService {

    private static SalesServiceImpl instance;
    private SalesDAO salesDAO;

    // 싱글톤 패턴을 위한 private 생성자
    private SalesServiceImpl() {
        salesDAO = new SalesDAOImpl();  // DAO 구현체를 주입
    }

    // 싱글톤 인스턴스를 반환하는 메서드
    public static SalesServiceImpl getInstance() {
        if (instance == null) {
            synchronized (SalesServiceImpl.class) {
                if (instance == null) {
                    instance = new SalesServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void addSale(SalesVO sale) {
        salesDAO.insertSale(sale);
    }

    @Override
    public void updateSale(SalesVO sale) {
        salesDAO.updateSale(sale);
    }

    @Override
    public void deleteSale(String saleId) {
        salesDAO.deleteSale(saleId);
    }

    @Override
    public SalesVO getSaleById(String saleId) {
        return salesDAO.selectSaleById(saleId);
    }

    @Override
    public List<SalesVO> getAllSales() {
        return salesDAO.getAllSales();
    }
}
