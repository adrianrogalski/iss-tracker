package com.example.isstracker.service;

import com.example.isstracker.model.issnow.Velocity;
import com.example.isstracker.service.iss.IssVelocityService;
import com.example.isstracker.service.iss.IssVelocityServiceApi;
import com.example.isstracker.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IssVelocityServiceTest {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final IssVelocityService issVelocity =
            new IssVelocityServiceApi(sessionFactory);
    private static final int DELTA_TIME = 5000;

    @Test
    void shouldSaveVelocityIntoDatabase() {
        final Velocity velocity = issVelocity.getVelocity(DELTA_TIME);
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        final Query<Velocity> from_velocity = session.createQuery("from Velocity");
        final Velocity databaseVelocity = from_velocity.getSingleResult();
        assertEquals(velocity, databaseVelocity);
        transaction.rollback();
        session.close();
    }

    @Test
    void shouldCalculateVelocityDifferentThanZero(){
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        // given
        double velocity = issVelocity.getVelocity(DELTA_TIME).getValue();
        // when, then
        assertFalse(velocity == 0.0);
        transaction.rollback();
        session.close();
    }

}