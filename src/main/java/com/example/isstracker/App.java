package com.example.isstracker;

import com.example.isstracker.model.astros.Astros;
import com.example.isstracker.model.issnow.IssNow;
import com.example.isstracker.repository.ApiRepository;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        final URI ISS_URI = URI.create("http://api.open-notify.org/iss-now.json");
        final URI ASTROS_URI = URI.create("http://api.open-notify.org/astros.json");
        ApiRepository<IssNow> issNowRepo = new ApiRepository(IssNow.class);
        ApiRepository<Astros> astrosRepo = new ApiRepository(Astros.class);
        Optional<IssNow> iss = issNowRepo.get(ISS_URI);
        Optional<Astros> astros = astrosRepo.get(ASTROS_URI);
        System.out.println(iss.get());
        System.out.println(astros.get());
    }
}