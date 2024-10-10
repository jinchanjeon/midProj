package kr.or.ddit.dao;

import kr.or.ddit.vo.ScheduleInfoVo;

public interface ScheduleInfoMapper {
    ScheduleInfoVo getScheduleInfo(String schNo);
}
