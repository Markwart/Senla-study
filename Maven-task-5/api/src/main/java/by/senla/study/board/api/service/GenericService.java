package by.senla.study.board.api.service;

import java.util.List;

public interface GenericService<T, PK, D> {

	T getById(PK id);

	void update(T entity);

	void insert(T entity);

	void deleteById(PK id);

	List<T> selectAll();

	T getFullInfo(PK id);

	void deleteAll();

	void setFieldsAndUpdate(T entity, D dto);
}
