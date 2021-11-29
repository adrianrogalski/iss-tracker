package com.example.isstracker.service;

import com.example.isstracker.model.issnow.Velocity;
import com.example.isstracker.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IssVelocityServiceTest {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final IssVelocityInterface issVelocity =
            new IssVelocityService(sessionFactory);
    @Test
    void shouldSaveVelocityIntoDatabase() {
        Velocity velocity = issVelocity.getVelocity();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Velocity> from_velocity = session.createQuery("from Velocity");
        Velocity databaseVelocity = from_velocity.getSingleResult();
        assertEquals(velocity, databaseVelocity);
        transaction.rollback();
        session.close();
    }

    @Test
    void shouldCalculateVelocityDifferentThanZero(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        // given
        double velocity = issVelocity.getVelocity().getValue();
        // when, then
        assertFalse(velocity == 0.0);
        transaction.rollback();
        session.close();
    }

}