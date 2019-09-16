package by.senla.study.api.dao;

import java.util.List;

public interface GenericDao<T, PK> {

	T getByID(PK id);

	void update(T entity);

	void insert(T entity);

	void delete (T entity);

	List<T> selectAll();

	void merge(T entity);

	T getFullInfo(PK id);

}
