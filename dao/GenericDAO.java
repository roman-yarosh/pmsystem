package net.proselyte.pmsystem.dao;

/**
 * Generic DAO interface. Used as a base class for all DAO classes.
 *
 * @author Eugene Suleimanov
 */
public interface GenericDAO<T, ID> {

    T getById(ID id);

    void save(T entity);

    void update(T entity);

    void remove(T entity);
}