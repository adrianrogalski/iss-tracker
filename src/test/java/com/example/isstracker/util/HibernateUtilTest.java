package com.example.isstracker.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateUtilTest {

    @Test
    void testConnection() {
        // given
        final SessionFactory factory = HibernateUtil.getSessionFactory();
        final Session session = factory.openSession();
        // when
        final NativeQuery query = session.createSQLQuery("SELECT 1");
        final List resultList = query.getResultList();
        // then
        assertEquals(1, resultList.get(0));
    }
}