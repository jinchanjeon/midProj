package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.MovieVo;

public interface MovieMapper {
    // 랭크 순으로 영화 목록을 가져오는 메서드
    List<MovieVo> getMovieList();
}