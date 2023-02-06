package dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<K, T extends Serializable> {

	T findOne(final K id);
	List<T> findAll();
	void save(final T entity);
	T update(final T entity);
	void deleteById(final K entityId);
}