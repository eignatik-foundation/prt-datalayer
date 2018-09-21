package space.eignatik.prt.datalayer.dataTools.dao;

import space.eignatik.prt.datalayer.dataTools.session.ISessionFactoryUtil;
import space.eignatik.prt.datalayer.dataTools.entities.IEntity;

import java.util.List;

public interface IDAO<T extends IEntity> {
    void add(T item);
    T get(Class<T> type, int id);
    List<T> getAll(Class<T> type);
    void update(T item);
    T delete(Class<T> type, int id);
    void setFactoryUtil(ISessionFactoryUtil factoryUtil);
}
