package com.example.isstracker.util;

import com.example.isstracker.model.astros.Astros;
import com.example.isstracker.model.astros.Person;
import com.example.isstracker.model.issnow.IssNow;
import com.example.isstracker.model.issnow.IssPosition;
import com.example.isstracker.model.issnow.Velocity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    private HibernateUtil(){

    }

    private static final SessionFactory factory;

    static {
        try {
            final Configuration configuration = new Configuration();
            configuration.setProperties(loadHibernateProperties());
            configureEntities(configuration);
            factory = configuration.buildSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    private static void configureEntities(Configuration configuration){
        configuration.addAnnotatedClass(IssNow.class);
        configuration.addAnnotatedClass(IssPosition.class);
        configuration.addAnnotatedClass(Astros.class);
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(Velocity.class);
    }

    private static Properties loadHibernateProperties() throws IOException {
        final Properties properties = new Properties();
        properties.load(HibernateUtil.class.getClassLoader().getResourceAsStream("hibernate.properties"));
        return properties;
    }
}
