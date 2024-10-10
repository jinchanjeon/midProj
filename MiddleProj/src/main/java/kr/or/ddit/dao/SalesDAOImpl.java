package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mybatis.MybatisUtil;
import kr.or.ddit.vo.SalesVO;

public class SalesDAOImpl implements SalesDao {
	
	private static SalesDAOImpl instance;
	private SalesDAOImpl() {
		
	}
    @Override
    public void insertSale(SalesVO sale) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession()) {
            sqlSession.insert("kr.or.ddit.mapper.SalesMapper.insertSale", sale);
            sqlSession.commit();
        }
    }

    @Override
    public void updateSale(SalesVO sale) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession()) {
            sqlSession.update("kr.or.ddit.mapper.SalesMapper.updateSale", sale);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteSale(String saleId) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession()) {
            sqlSession.delete("kr.or.ddit.mapper.SalesMapper.deleteSale", saleId);
            sqlSession.commit();
        }
    }

    @Override
    public SalesVO selectSaleById(String saleId) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession()) {
            return sqlSession.selectOne("kr.or.ddit.mapper.SalesMapper.selectSaleById", saleId);
        }
    }

    @Override
    public List<SalesVO> getAllSales() {
        try (SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("kr.or.ddit.mapper.SalesMapper.getAllSales");
        }
    }
}
