package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.TheaterVo;

public interface TheaterMapper {
    List<TheaterVo> getTheatersByRegion(String regNo);
}
