package com.example.isstracker.service;

import com.example.isstracker.model.issnow.IssNow;
import com.example.isstracker.repository.ApiRepository;
import com.example.isstracker.util.FormattedTimestamp;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public class IssLocationServiceApi implements IssLocationService {
    private final SessionFactory factory;
    private final URI ISS_URI = URI.create("http://api.open-notify.org/iss-now.json");
    private final ApiRepository<IssNow> issNowRepo = new ApiRepository(IssNow.class);
    private Optional<IssNow> data;
    public IssLocationServiceApi(SessionFactory factory) {
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
            return FormattedTimestamp.of(data.get().getTimestamp()).get().toString();
        }
        return "couldn't convert time properly";
    }


}
