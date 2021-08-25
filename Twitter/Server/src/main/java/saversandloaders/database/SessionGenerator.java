package saversandloaders.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionGenerator {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    public static Session getHibernateSession() {
        Session session = sessionFactory.openSession();
        return session;
    }
}
