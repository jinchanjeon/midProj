package kr.or.ddit.dao;

import java.util.List;
import kr.or.ddit.vo.SeatVo;

public interface SeatMapper {
    List<SeatVo> getAllSeats(); // 모든 좌석 가져오기
    SeatVo getSeatByNo(String seatNo); // 좌석 번호로 특정 좌석 가져오기
    void reserveSeat(SeatVo seat); // 좌석 예약
    void cancelReservation(String seatNo); // 좌석 예약 취소
}
