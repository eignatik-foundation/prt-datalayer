package space.eignatik.prt.datalayer.dataTools.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface ISessionFactoryUtil {
    SessionFactory getSessionFactory();
    Session getCurrentSession();
}
