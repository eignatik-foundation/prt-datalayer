package space.eignatik.prt.datalayer.dataTools.dao;

import org.hibernate.Session;
import space.eignatik.prt.datalayer.dataTools.session.ISessionFactoryUtil;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtil;
import space.eignatik.prt.datalayer.modelEntities.IEntity;

import java.util.List;

public interface IDAO<T extends IEntity> {
    T add(T item);
    T get(Class<T> type, int id);
    List<T> getAll();
    T delete(int id);
    T update(T item);
    void setFactoryUtil(ISessionFactoryUtil factoryUtil);
}
