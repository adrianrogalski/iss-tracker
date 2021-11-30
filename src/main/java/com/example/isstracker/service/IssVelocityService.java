package com.example.isstracker.service;

import com.example.isstracker.model.issnow.IssNow;
import com.example.isstracker.model.issnow.Velocity;
import com.example.isstracker.repository.ApiRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.Math.cos;
import static java.lang.Thread.sleep;

public class IssVelocityService implements IssVelocityInterface {
    private final SessionFactory factory;
    private final URI ISS_URI = URI.create("http://api.open-notify.org/iss-now.json");
    private final ApiRepository<IssNow> issNowRepo = new ApiRepository(IssNow.class);
    private final static double EARTH_RADIUS = 6378000;

    public IssVelocityService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Velocity getVelocity(int deltaTimeMills) {
        try {
            List<IssNow> issPair = generatePairOfIss(deltaTimeMills);
            double velocityValue = calculateVelocity(issPair.get(0), issPair.get(1));
            Velocity velocity = new Velocity(velocityValue, issPair.get(0).getTimestamp(), deltaTimeMills);
            saveIntoDatabase(velocity);
            return velocity;
        } catch (InterruptedException | IOException e) {
            throw new IllegalStateException("Server error");
        }
    }

    private void saveIntoDatabase(Velocity velocity) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        session.save(velocity);
        transaction.commit();
        session.close();
    }

    private List<IssNow> generatePairOfIss(int millis) throws IOException, InterruptedException {
        final IssNow issT1 = issNowRepo.findByURI(ISS_URI).get();
        sleep(millis);
        final IssNow issT2 = issNowRepo.findByURI(ISS_URI).get();
        return List.of(issT1, issT2);
    }

    private Double calculateVelocity(IssNow issT1, IssNow issT2) {
        final double latitude_t1 = toRadians(issT1.getIss_position().getLatitude());
        final double longitude_t1 = toRadians(issT1.getIss_position().getLongitude());
        long t1 = issT1.getTimestamp();
        double latitude_t2 = toRadians(issT2.getIss_position().getLatitude());
        double longitude_t2 = toRadians(issT2.getIss_position().getLongitude());
        long t2 = issT2.getTimestamp();
        // http://edwilliams.org/avform147.htm The great circle formula
        return calculateDistanceWithTheGreatCircleFormula(latitude_t1, longitude_t1, latitude_t2, longitude_t2) / dt(t2, t1);

    }

    private double calculateDistanceWithTheGreatCircleFormula(double latitude_t1, double longitude_t1, double latitude_t2, double longitude_t2) {
        return (acos(sin(latitude_t1)*sin(latitude_t2) + (cos(latitude_t1) * cos(latitude_t2) * cos(longitude_t1- longitude_t2))) * EARTH_RADIUS);
    }
    private double dt (double t1, double t2) {
        return t2-t1;
    }
}
