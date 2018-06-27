package space.eignatik.prt.datalayer.dataTools.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.eignatik.prt.datalayer.dataTools.session.ISessionFactoryUtil;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtil;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;
import space.eignatik.prt.datalayer.modelEntities.IEntity;

import java.util.List;

/**
 * Generic DAO to provide with default CRUD operations for other entities DAOs.
 * By default it uses session factory for production database. DB can be switched to test DB
 * by switching the SessionFactoryUtil to SessionFactoryUtilLocal
 * @see ISessionFactoryUtil
 * @see SessionFactoryUtil
 * @see SessionFactoryUtilLocal
 * @param <T> is project entity classes
 */
abstract class GenericDAO<T extends IEntity> implements IDAO<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDAO.class.getName());
    private static final String COMMITED_STATUS = "COMMITED";
    private ISessionFactoryUtil factoryUtil = new SessionFactoryUtil();

    protected Session getCurrentSession() {
        return factoryUtil.
                getSessionFactory()
                .getCurrentSession();
    }

    @Override
    public T add(T item) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        LOGGER.info(transaction.getStatus().name() + " item: " + item);
        return COMMITED_STATUS.equals(transaction.getStatus().name())?
                item : null;
    }

    @Override
    public T get(Class<T> type, int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        T item = session.get(type, id);
        transaction.commit();
        LOGGER.info("Item from DB: " + item);
        return item;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public T delete(int id) {
        return null;
    }

    @Override
    public T update(T item) {
        return null;
    }

    @Override
    public void setFactoryUtil(ISessionFactoryUtil factoryUtil) {
        this.factoryUtil = factoryUtil;
    }
}
