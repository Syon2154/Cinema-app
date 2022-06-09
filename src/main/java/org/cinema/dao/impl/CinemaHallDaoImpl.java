package org.cinema.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import org.cinema.dao.CinemaHallDao;
import org.cinema.exception.DataProcessingException;
import org.cinema.lib.Dao;
import org.cinema.model.CinemaHall;
import org.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            return cinemaHall;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert a cinema hall: " + cinemaHall, ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<CinemaHall> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(CinemaHall.class, id));
        } catch (Exception ex) {
            throw new DataProcessingException("Can't get a cinema hall by id: " + id, ex);
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            throw new DataProcessingException("Can't get all cinema halls", ex);
        }
    }
}
