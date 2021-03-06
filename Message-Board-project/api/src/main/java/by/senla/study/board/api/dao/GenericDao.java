package by.senla.study.board.api.dao;

import java.util.List;

public interface GenericDao<T, PK> {

	T getById(PK id);

	void update(T entity);

	void insert(T entity);

	void delete (T entity);

	List<T> selectAll();

	T getFullInfo(PK id);

	void deleteAll();
}
