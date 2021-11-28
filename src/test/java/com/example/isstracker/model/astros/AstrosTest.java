package com.example.isstracker.model.astros;

import com.example.isstracker.repository.ApiRepository;
import com.example.isstracker.repository.SimpleGenericRepository;
import com.example.isstracker.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class AstrosTest {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final static URI uri = URI.create("http://api.open-notify.org/astros.json");
    private final SimpleGenericRepository<Astros> repository = new ApiRepository(Astros.class);
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void before() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    void after() {
        transaction.rollback();
        session.close();
    }

    @Test
    void shouldSaveAstrosIntoDatabase() throws IOException, InterruptedException {
        // given
        Astros astros = repository.findByURI(uri).get();

        // when
        saveAndFlush(astros);
        Astros databaseRead = session.get(Astros.class, astros.getId());

        // then
        assertEquals(astros, databaseRead);
    }

    void shouldHaveSuccessValueInTableMessageField() throws IOException, InterruptedException {
        // given
        Astros astros = repository.findByURI(uri).get();

        // when
        saveAndFlush(astros);
        Astros databaseRead = session.get(Astros.class, astros.getId());

        String message = databaseRead.getMessage();
        // then
        assertEquals("success", message);
    }

    @Test
    void peopleListShouldntBeEmpty() throws IOException, InterruptedException {
        // given
        Astros astros = repository.findByURI(uri).get();

        // when
        saveAndFlush(astros);
        Astros databaseRead = session.get(Astros.class, astros.getId());
        assertFalse(databaseRead.getPeople().isEmpty());
    }

    private void saveAndFlush(Astros astros) {
        session.save(astros);
        session.flush();
        session.clear();
    }





}