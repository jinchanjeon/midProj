package kr.or.ddit.movie.dao;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.movie.Movie;

public class MovieDao {
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void updateMovie(int id, Movie movie) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId() == id) {
                movies.set(i, movie);
                break;
            }
        }
    }

    public void deleteMovie(int id) {
        movies.removeIf(movie -> movie.getId() == id);
    }

    public List<Movie> getAllMovies() {
        return movies;
    }
}
