package space.eignatik.prt.datalayer.dataTools.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;

public class DAOTestUtil {
    static void deleteAllFromTable(String tableName) {
        String deleteQuery = "delete from " + tableName;
        Session session = new SessionFactoryUtilLocal()
                .getSessionFactory()
                .getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery(deleteQuery)
                .executeUpdate();
        transaction.commit();
    }
}
