package com.example.isstracker.service;

import com.example.isstracker.model.issnow.IssNow;
import com.example.isstracker.repository.ApiRepository;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static com.example.isstracker.util.TimestampConverter.convert;

public class IssRepoService implements IssRepoInterface {
    private final SessionFactory factory;
    private final URI ISS_URI = URI.create("http://api.open-notify.org/iss-now.json");
    private final ApiRepository<IssNow> issNowRepo = new ApiRepository(IssNow.class);
    private Optional<IssNow> data;
    public IssRepoService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void getCurrentIssData() {
        try {
            data = issNowRepo.findByURI(ISS_URI);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public double getLatitude() {
        if (data.isPresent()) {
            return data.get().getIss_position().getLatitude();
        }
        return 0;
    }

    public double getLongitude() {
        if (data.isPresent()) {
            return data.get().getIss_position().getLongitude();
        }
        return 0;
    }

    public String getTimestamp() {
        if (data.isPresent()) {
            return convert(data.get().getTimestamp());
        }
        return "couldn't convert time properly";
    }
}
