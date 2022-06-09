package org.cinema.dao.impl;

import org.cinema.dao.TicketDao;
import org.cinema.exception.DataProcessingException;
import org.cinema.lib.Dao;
import org.cinema.model.Ticket;
import org.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert a ticket: " + ticket, ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
