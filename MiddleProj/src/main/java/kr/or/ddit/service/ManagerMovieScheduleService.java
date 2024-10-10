package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.MovieScheduleVO;

public interface ManagerMovieScheduleService {

    // 영화 스케줄 추가
    void addSchedule(MovieScheduleVO movieSchedule);

    // 영화 스케줄 수정
    void updateSchedule(MovieScheduleVO movieSchedule);

    // 영화 스케줄 삭제
    void deleteSchedule(String scheduleId);

    // 특정 영화 스케줄 조회
    MovieScheduleVO getScheduleById(String scheduleId);

    // 모든 영화 스케줄 조회
    List<MovieScheduleVO> getAllSchedules();
}
