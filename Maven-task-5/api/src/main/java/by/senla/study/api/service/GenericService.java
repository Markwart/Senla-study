package by.senla.study.api.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface GenericService <T, PK> {
	
	T createEntity();

	T getByID(PK id);
	
	@Transactional
	void update(T entity);

	@Transactional
	void insert(T entity);

	@Transactional
	void deleteByID(PK id);
	
	List<T> selectAll();

	void exportToCSV();

	List<T> importFromCSV();

	@Transactional
	void merge(T entity);

	T getFullInfo(PK id);

}
