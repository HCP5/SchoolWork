package repository.database;

import entities.domain.Masina;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.MasinaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateMasina implements MasinaRepository {
    private static SessionFactory sessionFactory;

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exceptie "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }


    static void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @Override
    public Masina findOne(Integer integer) {
        try(Session  session = sessionFactory.openSession()){
            Masina masina=new Masina();
            session.beginTransaction();
            List<Masina> result = session.createQuery("from Masina", Masina.class).list();
            session.getTransaction().commit();
            result=result.stream().filter(masina1 -> masina1.getId().equals(integer)).collect(Collectors.toList());
            masina=result.get(0);
            return masina;
        }
    }

    @Override
    public Iterable<Masina> findAll() {
        initialize();
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Masina> result = session.createQuery("from Masina", Masina.class).list();
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void save(Masina elem) {
        initialize();
        try (Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.save(elem);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Integer integer, Masina elem) {
        initialize();
        try (Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.update(elem);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Integer integer) {
        initialize();
        try (Session session=sessionFactory.openSession()){
            session.beginTransaction();
            Masina masina=findOne(integer);
            session.delete(masina);
            session.getTransaction().commit();
        }
    }
}
