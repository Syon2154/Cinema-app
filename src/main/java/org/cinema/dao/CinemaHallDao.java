package org.cinema.dao;

import java.util.List;
import java.util.Optional;
import org.cinema.model.CinemaHall;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    Optional<CinemaHall> get(Long id);

    List<CinemaHall> getAll();
}
