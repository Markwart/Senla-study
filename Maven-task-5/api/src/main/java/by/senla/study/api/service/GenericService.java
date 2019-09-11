package by.senla.study.api.service;

import java.util.List;

public interface GenericService <T, PK> {
	
	T createEntity();

	T getByID(PK id);
	
	void update(T entity);

	void insert(T entity);

	void deleteByID(PK id);
	
	List<T> selectAll();

	void exportToCSV();

	List<T> importFromCSV();

	void merge(T entity);

}
