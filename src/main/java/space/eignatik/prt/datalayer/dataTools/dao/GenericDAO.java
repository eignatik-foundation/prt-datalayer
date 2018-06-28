package space.eignatik.prt.datalayer.dataTools.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import space.eignatik.prt.datalayer.dataTools.session.ISessionFactoryUtil;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtil;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;
import space.eignatik.prt.datalayer.modelEntities.IEntity;

import javax.transaction.Transactional;
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
@SuppressWarnings("unchecked")
@Repository
@Transactional
abstract class GenericDAO<T extends IEntity> implements IDAO<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDAO.class.getName());
    private ISessionFactoryUtil factoryUtil = new SessionFactoryUtil();

    @Override
    public void add(T item) {
        Transaction transaction = getCurrentSession().beginTransaction();
        getCurrentSession().save(item);
        transaction.commit();
        LOGGER.info(transaction.getStatus().name() + " item: " + item);
    }

    @Override
    public T get(Class<T> type, int id) {
        Transaction transaction = getCurrentSession().beginTransaction();
        T item = getCurrentSession().get(type, id);
        transaction.commit();
        LOGGER.info("Item from DB: " + item);
        return item;
    }

    @Override
    public List<T> getAll(Class<T> type) {
        Transaction transaction = getCurrentSession().beginTransaction();
        List<T> resultItems = getCurrentSession()
                .createQuery("from " + type.getSimpleName())
                .list();
        transaction.commit();
        LOGGER.info((resultItems != null? resultItems.size() : 0) + " items obtained from db");
        return resultItems;
    }

    @Override
    public T delete(Class<T> type, int id) {
        Transaction transaction = getCurrentSession().beginTransaction();
        T item = getCurrentSession().get(type, id);
        if (item != null) {
            getCurrentSession().delete(item);
        }
        transaction.commit();
        LOGGER.info("Item from DB: " + item + " has been removed.");
        return item;
    }

    @Override
    public void update(T item) {
        Transaction transaction = getCurrentSession().beginTransaction();
        getCurrentSession().update(item);
        transaction.commit();
        LOGGER.info("Item from DB: " + item + " has been updated.");
    }

    @Override
    public void setFactoryUtil(ISessionFactoryUtil factoryUtil) {
        this.factoryUtil = factoryUtil;
    }

    protected Session getCurrentSession() {
        return factoryUtil.
                getSessionFactory()
                .getCurrentSession();
    }
}
