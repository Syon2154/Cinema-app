package org.cinema.service.impl;

import java.util.List;
import org.cinema.dao.CinemaHallDao;
import org.cinema.lib.Inject;
import org.cinema.lib.Service;
import org.cinema.model.CinemaHall;
import org.cinema.service.CinemaHallService;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public CinemaHall get(Long id) {
        return cinemaHallDao.get(id).get();
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
