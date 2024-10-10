package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.MovieDaoImpl;
import kr.or.ddit.dao.iMovieDao;
import kr.or.ddit.vo.ActorVo;
import kr.or.ddit.vo.BoxofficeVo;
import kr.or.ddit.vo.DirectorVo;
import kr.or.ddit.vo.GenreVo;
import kr.or.ddit.vo.MovGenVo;
import kr.or.ddit.vo.MovieActorVo;
import kr.or.ddit.vo.MovieDirectorVo;
import kr.or.ddit.vo.MovieVo;

public class MovieServiceImpl implements iMovieService{

	private static MovieServiceImpl instance;

	private MovieServiceImpl() {

	}

	public static MovieServiceImpl getInstance() {
		if (instance == null) {
			instance = new MovieServiceImpl();

		}
		return instance;
	}
	
	iMovieDao movieDao = MovieDaoImpl.getInstance();

	@Override
	public int insertMovie(MovieVo movieVo) {
		return movieDao.insertMovie(movieVo);
	}

	@Override
	public List<MovieVo> movieList(MovieVo searchMovie) {
		return movieDao.movieList(searchMovie);
	}

	@Override
	public MovieVo getMovie(MovieVo movieNo) {
		return movieDao.getMovie(movieNo);
	}

	@Override
	public List<MovieVo> getMovieName(MovieVo movieName) {
		return movieDao.getMovieName(movieName);
	}

	@Override
	public int insertBox(BoxofficeVo boxVo) {
		return movieDao.insertBox(boxVo);
	}

	@Override
	public MovieVo getMovieNameRelease(MovieVo movie) {
		return movieDao.getMovieNameRelease(movie);
	}

	@Override
	public List<BoxofficeVo> boxList() {
		return movieDao.boxList();
	}

	@Override
	public List<MovieVo> boxInfo() {
		return movieDao.boxInfo();
	}

	@Override
	public BoxofficeVo boxOfficeExists(String box) {
		return movieDao.boxOfficeExists(box);
	}

	@Override
	public int getTotal() {
		return movieDao.getTotal();
	}

	@Override
	public Integer selectGenre(String gen) {
		return movieDao.selectGenre(gen);
	}

	@Override
	public void insertGenre(String gen) {
		movieDao.insertGenre(gen);
	}

	@Override
	public void insertMovGen(MovGenVo MG) {
		movieDao.insertMovGen(MG);
	}

	@Override
	public void insertDir(DirectorVo dir) {
		movieDao.insertDir(dir);
	}

	@Override
	public void insertMovDir(MovieDirectorVo dir) {
		movieDao.insertMovDir(dir);
	}

	@Override
	public DirectorVo selectDir(int dir) {
		return movieDao.selectDir(dir);
	}

	@Override
	public MovGenVo getMovGen(MovGenVo mg) {
		return movieDao.getMovGen(mg);
	}

	@Override
	public MovieDirectorVo getMovDir(MovieDirectorVo md) {
		return movieDao.getMovDir(md);
	}

	@Override
	public void insertAct(ActorVo act) {
		movieDao.insertAct(act);
	}

	@Override
	public void insertMovAct(MovieActorVo mavo) {
		movieDao.insertMovAct(mavo);
	}

	@Override
	public ActorVo selectAct(int act) {
		return movieDao.selectAct(act);
	}

	@Override
	public MovieActorVo getMovAct(MovieActorVo ma) {
		return movieDao.getMovAct(ma);
	}

	@Override
	public List<GenreVo> selectAllGen() {
		return movieDao.selectAllGen();
	}

	@Override
	public List<MovieVo> getMoviesByGenre(MovieVo pageger) {
		return movieDao.getMoviesByGenre(pageger);
	}

	@Override
	public List<GenreVo> selectGenFromMovNo(String movNo) {
		return movieDao.selectGenFromMovNo(movNo);
	}

	@Override
	public List<ActorVo> selectActFromMovNo(String movNo) {
		return movieDao.selectActFromMovNo(movNo);
	}

	@Override
	public List<DirectorVo> selectDirFromMovNo(String movNo) {
		return movieDao.selectDirFromMovNo(movNo);
	}

	@Override
	public List<MovieVo> getMoviesByGenreAndSearch(MovieVo paging) {
		return movieDao.getMoviesByGenreAndSearch(paging);
	}

	@Override
	public List<MovieVo> getMoviesBySearch(MovieVo paging) {
		return movieDao.getMoviesBySearch(paging);
	}

	@Override
	public void updateMovie(MovieVo movie) {
		movieDao.updateMovie(movie);
	}

	@Override
	public List<MovieVo> selmoviebygen(String genName) {
		return movieDao.selmoviebygen(genName);
	}

	@Override
	public List<GenreVo> selgenbymovie(String movieNo) {
		return movieDao.selgenbymovie(movieNo);
	}

	
	
}

