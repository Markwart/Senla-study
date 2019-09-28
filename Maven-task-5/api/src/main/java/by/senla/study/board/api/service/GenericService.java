package by.senla.study.board.api.service;

import java.util.List;

public interface GenericService<T, PK> {

	T getByID(PK id);

	void update(T entity);

	void insert(T entity);

	void deleteByID(PK id);

	List<T> selectAll();

	void exportToCSV();

	List<Object> importFromCSV();

	void merge(T entity);

	T getFullInfo(PK id);

}
