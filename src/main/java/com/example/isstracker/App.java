package com.example.isstracker;
import com.example.isstracker.model.issnow.Velocity;
import com.example.isstracker.service.iss.IssLocationService;
import com.example.isstracker.service.iss.IssLocationServiceApi;
import com.example.isstracker.service.iss.IssVelocityService;
import com.example.isstracker.service.iss.IssVelocityServiceApi;
import com.example.isstracker.service.people.PeopleInSpaceService;
import com.example.isstracker.service.people.PeopleInSpaceServiceApi;
import com.example.isstracker.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class App {
    public static void main(String[] args) {
        final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        IssVelocityService velocityCalculator = new IssVelocityServiceApi(sessionFactory);
        IssLocationService issLocationService = new IssLocationServiceApi(sessionFactory);
        PeopleInSpaceService peopleInSpaceService = new PeopleInSpaceServiceApi(sessionFactory);

        System.out.println("\tInternational Space Station Tracker");
        System.out.println("\t-----------------------------------");
        System.out.println("\tActual Data");
        issLocationService.getCurrentIssData();
        System.out.println("\tlatitude: " + issLocationService.getLatitude());
        System.out.println("\tlongitude: " + issLocationService.getLongitude());
        System.out.println("\tat time: " + issLocationService.getTimestamp());
        Velocity velocity = velocityCalculator.getVelocity(5000);
        System.out.println("\t velocity: " + velocity.getValue() + "m/s from delta time: " + velocity.getDeltaTime() + " at " + velocity.getFormattedTimestamp());
        System.out.println("\t number of people in space: " + peopleInSpaceService.numberOfPeopleInSpace());
        System.out.println("\t people in space: ");
        peopleInSpaceService.peopleInSpace().forEach(System.out::println);
        System.out.println("\t people on iss: ");
        peopleInSpaceService.peopleOnIss().forEach(System.out::println);
    }
}