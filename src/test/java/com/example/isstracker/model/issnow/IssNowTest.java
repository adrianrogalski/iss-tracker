package com.example.isstracker.model.issnow;

import com.example.isstracker.repository.ApiRepository;
import com.example.isstracker.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class IssNowTest {
    private final SessionFactory factory = HibernateUtil.getSessionFactory();
    private final URI ISS_URI = URI.create("http://api.open-notify.org/iss-now.json");
    private final ApiRepository<IssNow> issNowRepo = new ApiRepository(IssNow.class);
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void before(){
        session = factory.openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    void after() {
        transaction.rollback();
        session.close();
    }

    @Test
    void shouldSaveIssNowIntoDatabase() throws IOException, InterruptedException {
        // given
        IssNow iss = issNowRepo.findByURI(ISS_URI).get();
        // when
        saveAndFlush(iss);
        final IssNow readIssNow = session.get(IssNow.class, iss.getId());
        // then
        System.out.println(readIssNow);
        assertEquals(iss, readIssNow);
    }

    @Test
    void shouldHaveSuccessValueInTableMessageField() throws IOException, InterruptedException {
        // given
        IssNow iss = issNowRepo.findByURI(ISS_URI).get();
        // when
        saveAndFlush(iss);
        final IssNow readIssNow = session.get(IssNow.class, iss.getId());
        String message = readIssNow.getMessage();
        // then
        assertEquals("success", message);
    }

    @Test
    void issNowPositionFieldShouldHaveEmptyLatitudeAndLongitude() throws IOException, InterruptedException {
        // given
        IssNow iss = issNowRepo.findByURI(ISS_URI).get();
        // when
        saveAndFlush(iss);
        final IssNow readIssNow = session.get(IssNow.class, iss.getId());
        IssPosition position = readIssNow.getIss_position();
        assertFalse(position.getLatitude().isEmpty());
        assertFalse(position.getLongitude().isEmpty());
    }

    private void saveAndFlush(IssNow iss) {
        session.save(iss);
        session.flush();
        session.clear();
    }

}