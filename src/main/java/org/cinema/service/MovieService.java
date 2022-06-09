package org.cinema.service;

import java.util.List;
import org.cinema.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    Movie get(Long id);

    List<Movie> getAll();
}
