package kr.or.ddit.movie.service;

import java.util.List;

import kr.or.ddit.movie.Movie; // Movie 클래스 import
import kr.or.ddit.movie.dao.MovieDao; // MovieDao 클래스 import

public class MovieService {
    private MovieDao movieDao = new MovieDao();

    public void addMovie(Movie movie) {
        movieDao.addMovie(movie);
    }

    public void updateMovie(int id, Movie movie) {
        movieDao.updateMovie(id, movie);
    }

    public void deleteMovie(int id) {
        movieDao.deleteMovie(id);
    }

    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }
}
