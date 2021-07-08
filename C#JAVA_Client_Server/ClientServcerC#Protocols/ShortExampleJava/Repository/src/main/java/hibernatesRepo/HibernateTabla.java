package hibernatesRepo;

import domain.Jucator;
import domain.Tabla;
import hibernatesRepo.interfaces.ITablaRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateTabla implements ITablaRepo {
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
    public Tabla findOne(Integer integer) {
        initialize();
        try (Session session=sessionFactory.openSession()){
            session.beginTransaction();
            Tabla tabla = (Tabla) session.createQuery("from Tabla where id=?").setParameter(0,integer).getSingleResult();
            session.getTransaction().commit();
            close();
            return tabla;
        }
    }

    @Override
    public Iterable<Tabla> findAll() {
        initialize();
        try (Session session=sessionFactory.openSession()){
            session.beginTransaction();
            List<Tabla> tabla= session.createQuery("from Tabla ",Tabla.class).list();
            session.getTransaction().commit();
            close();
            return tabla;
        }
    }

    @Override
    public void save(Tabla elem) {
        initialize();
        try (Session   session=sessionFactory.openSession()){
            session.beginTransaction();
            session.save(elem);
            session.getTransaction().commit();
            close();
        }
    }

    @Override
    public void update(Integer integer, Tabla elem) {
        delete(integer);
        save(elem);
    }

    @Override
    public void delete(Integer integer) {
        initialize();
        try (Session session=sessionFactory.openSession()){
            session.beginTransaction();
            Tabla user=findOne(integer);
            session.delete(user);
            session.getTransaction().commit();
            close();
        }
    }
}
