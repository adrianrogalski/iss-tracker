package com.example.isstracker;
import com.example.isstracker.model.issnow.IssNow;
import com.example.isstracker.service.IssRepoInterface;
import com.example.isstracker.service.IssRepoService;
import com.example.isstracker.service.IssVelocityService;
import com.example.isstracker.service.IssVelocityInterface;
import com.example.isstracker.util.HibernateUtil;
import org.hibernate.SessionFactory;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        IssVelocityInterface velocityCalculator = new IssVelocityService(sessionFactory);
        IssRepoInterface issRepoInterface = new IssRepoService(sessionFactory);
        System.out.println("\tInternational Space Station Tracker\t");
        System.out.println("\t-----------------------------------\t");
        System.out.println("\tActual Data\t");
        issRepoInterface.getCurrentIssData();
        System.out.println("\tlatitude: " + issRepoInterface.getLatitude() + '\t');
        System.out.println("\tlongitude: " + issRepoInterface.getLongitude() + '\t');
        System.out.println("\tat time: " + issRepoInterface.getTimestamp() + '\t');
        System.out.println("\t velocity: " + velocityCalculator.getVelocity().getValue() + "m/s" + '\t');
    }
}