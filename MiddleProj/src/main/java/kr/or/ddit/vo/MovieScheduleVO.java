package kr.or.ddit.vo;

import lombok.Data;

@Data
public class MovieScheduleVO {
    private String scheduleId;
    private String movieId;
    private String screeningTime;
    private String screenId;
    private int seatAvailable;

    