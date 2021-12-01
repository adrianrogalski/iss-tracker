package com.example.isstracker.service.people;

import com.example.isstracker.exceptions.AstrosRepositoryObjectConversionException;
import com.example.isstracker.model.astros.Astros;
import com.example.isstracker.model.astros.Person;
import com.example.isstracker.repository.ApiRepository;
import com.example.isstracker.repository.SimpleGenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class PeopleInSpaceServiceApi implements PeopleInSpaceService {
    private final static URI ASTROS_URI = URI.create("http://api.open-notify.org/astros.json");
    SimpleGenericRepository<Astros> peopleInSpaceRepo = new ApiRepository<>(Astros.class);
    private final SessionFactory factory;

    public PeopleInSpaceServiceApi(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public int numberOfPeopleInSpace() {
        Session session = factory.openSession();
        List<Astros> astrosDatabase = session.createQuery("from Astros").getResultList();
        if (astrosDatabase.isEmpty()) {
            try {
                Astros astros = peopleInSpaceRepo.findByURI(ASTROS_URI).get();
                saveIntoDatabase(astros, session);
                return astros.getNumber();
            } catch (InterruptedException | IOException e) {
                throw new AstrosRepositoryObjectConversionException("Astros object couldn't be created");
            }
        }
        else {
            return astrosDatabase.get(0).getNumber();
        }
    }

    private void saveIntoDatabase(Astros astros, Session session){
        Transaction transaction = session.beginTransaction();
        session.save(astros);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Person> peopleInSpace() {
        return null;
    }

    @Override
    public List<Person> peopleOnIss() {
        return null;
    }
}
