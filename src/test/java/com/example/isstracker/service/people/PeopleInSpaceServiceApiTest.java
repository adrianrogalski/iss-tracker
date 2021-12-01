package com.example.isstracker.service.people;

import com.example.isstracker.model.astros.Astros;
import com.example.isstracker.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeopleInSpaceServiceApiTest {
    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    @Test
    void returnsNumberOfPeople() {
        PeopleInSpaceService peopleInSpaceService = new PeopleInSpaceServiceApi(factory);
        int numberOfPeopleInSpace = peopleInSpaceService.numberOfPeopleInSpace();
        assertTrue(numberOfPeopleInSpace > 0);
    }

    @Test
    void shouldSaveAstrosIntoDatabase(){
        // given
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        PeopleInSpaceService peopleInSpaceService = new PeopleInSpaceServiceApi(factory);
        peopleInSpaceService.numberOfPeopleInSpace();
        Query<Astros> from_astros = session.createQuery("from Astros");
        List<Astros> resultList = from_astros.getResultList();
        transaction.rollback();
        session.close();
        assertFalse(resultList.isEmpty());
    }

}