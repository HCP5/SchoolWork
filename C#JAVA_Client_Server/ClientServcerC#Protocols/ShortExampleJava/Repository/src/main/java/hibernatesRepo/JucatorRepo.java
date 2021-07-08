package hibernatesRepo;

import domain.Jucator;
import domain.User;
import hibernatesRepo.interfaces.IJucatorRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JucatorRepo implements IJucatorRepo {
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
    public Jucator findOne(Integer integer) {
        initialize();
        try (Session session=sessionFactory.openSession()){
            session.beginTransaction();
            Jucator user= (Jucator) session.createQuery("from Jucator where id=?").setParameter(0,integer).getSingleResult();
            session.getTransaction().commit();
            close();
            return user;
        }
    }

    @Override
    public Iterable<Jucator> findAll() {
        initialize();
        try (Session session=sessionFactory.openSession()){
            session.beginTransaction();
            List<Jucator> user= session.createQuery("from Jucator ",Jucator.class).list();
            session.getTransaction().commit();
            close();
            return user;
        }
    }

    @Override
    public void save(Jucator elem) {
        initialize();
        try (Session   session=sessionFactory.openSession()){
            session.beginTransaction();
            session.save(elem);
            session.getTransaction().commit();
            close();
        }
    }

    @Override
    public void update(Integer integer, Jucator elem) {
        delete(integer);
        save(elem);
    }

    @Override
    public void delete(Integer integer) {
        initialize();
        try (Session session=sessionFactory.openSession()){
            session.beginTransaction();
            Jucator user=findOne(integer);
            session.delete(user);
            session.getTransaction().commit();
            close();
        }
    }
}
