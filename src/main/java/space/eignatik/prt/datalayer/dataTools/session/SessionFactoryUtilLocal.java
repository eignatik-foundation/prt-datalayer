package space.eignatik.prt.datalayer.dataTools.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtilLocal implements ISessionFactoryUtil {
    private static SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("test_hibernate.cfg.xml")
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
