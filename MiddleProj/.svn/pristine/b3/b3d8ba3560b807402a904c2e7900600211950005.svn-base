package kr.or.ddit.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.Region;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mybatis.MybatisUtil;
import kr.or.ddit.vo.TheaterVo;

public class RegionDAO {
    public static List<Region> getAllRegions() {
        List<Region> regionList = new ArrayList<>();
        try (SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession()){
            regionList = sqlSession.selectList("kr.or.ddit.mapper.RegionMapper.getAllRegions");
        }
        return regionList;
    }
}

