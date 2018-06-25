package space.eignatik.prt.datalayer.dataTools.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.eignatik.prt.datalayer.dataTools.session.ISessionFactoryUtil;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtil;
import space.eignatik.prt.datalayer.modelEntities.IEntity;

import java.util.List;

public abstract class GenericDAO<T extends IEntity> implements IDAO<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDAO.class.getName());
    private static final String COMMITED_STATUS = "COMMITED";
    private ISessionFactoryUtil factoryUtil = new SessionFactoryUtil();

    Session getCurrentSession() {
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
        return getCurrentSession().get(type, id);
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
}