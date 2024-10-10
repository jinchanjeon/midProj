package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.ScheduleVo;

public interface ScheduleMapper {
    List<ScheduleVo> getSchedules(@Param("movieNo") String movieNo, @Param("schDate") String schDate, @Param("spotCode") String spotCode);
}
