package com.example.isstracker.repository;

import com.example.isstracker.model.astros.Astros;
import com.example.isstracker.model.issnow.IssNow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

class ApiRepositoryTest {

    @Test
    void findByURIShouldReturnOptionalWithIssObjectGivenValidIssNowURI() throws IOException, InterruptedException {
        // given
        final URI ISS_URI = URI.create("http://api.open-notify.org/iss-now.json");
        ApiRepository<IssNow> issNowRepo = new ApiRepository(IssNow.class);
        // when
        Optional<IssNow> iss = issNowRepo.findByURI(ISS_URI);
        // then
        Assertions.assertTrue(iss.isPresent());
    }

    @Test
    void findByURIShouldReturnOptionalWithAnAstrosObjectGivenValidAstrosURI() throws IOException, InterruptedException {
        // given
        final URI ASTROS_URI = URI.create("http://api.open-notify.org/astros.json");
        ApiRepository<Astros> astrosRepo = new ApiRepository(Astros.class);
        // when
        Optional<Astros> iss = astrosRepo.findByURI(ASTROS_URI);
        // then
        Assertions.assertTrue(iss.isPresent());
    }

    @Test
    void findByURIShouldReturnEmptyOptionalGivenWrongURI() throws IOException, InterruptedException {
        // given
        final URI ISS_URI = URI.create("http://api.open-notify.org/notindatabase.json");
        ApiRepository<IssNow> issNowRepo = new ApiRepository(IssNow.class);
        // when
        Optional<IssNow> iss = issNowRepo.findByURI(ISS_URI);
        // then
        Assertions.assertTrue(iss.isEmpty());
    }
}