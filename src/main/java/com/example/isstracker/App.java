package com.example.isstracker;
import com.example.isstracker.model.issnow.Velocity;
import com.example.isstracker.service.IssLocationServiceInterface;
import com.example.isstracker.service.IssLocationService;
import com.example.isstracker.service.IssVelocityService;
import com.example.isstracker.service.IssVelocityInterface;
import com.example.isstracker.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class App {
    public static void main(String[] args) {
        final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        IssVelocityInterface velocityCalculator = new IssVelocityService(sessionFactory);
        IssLocationServiceInterface issLocationServiceInterface = new IssLocationService(sessionFactory);
        System.out.println("\tInternational Space Station Tracker");
        System.out.println("\t-----------------------------------");
        System.out.println("\tActual Data");
        issLocationServiceInterface.getCurrentIssData();
        System.out.println("\tlatitude: " + issLocationServiceInterface.getLatitude());
        System.out.println("\tlongitude: " + issLocationServiceInterface.getLongitude());
        System.out.println("\tat time: " + issLocationServiceInterface.getTimestamp());
        Velocity velocity = velocityCalculator.getVelocity(5000);
        System.out.println("\t velocity: " + velocity.getValue() + "m/s from delta time: " + velocity.getDeltaTime() + " at " + velocity.getFormattedTimestamp());
    }
}