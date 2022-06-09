package org.cinema.dao.impl;

import java.util.List;
import org.cinema.dao.OrderDao;
import org.cinema.exception.DataProcessingException;
import org.cinema.lib.Dao;
import org.cinema.model.Order;
import org.cinema.model.User;
import org.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert order: " + order, ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "JOIN FETCH o.tickets t "
                    + "JOIN FETCH t.movieSession ms "
                    + "JOIN FETCH ms.movie "
                    + "JOIN FETCH ms.cinemaHall "
                    + "JOIN FETCH o.user "
                    + "WHERE o.user = :user ", Order.class);
            query.setParameter("user", user);
            return query.getResultList();
        } catch (Exception ex) {
            throw new DataProcessingException("Can't  get orders by user: " + user, ex);
        }
    }
}
