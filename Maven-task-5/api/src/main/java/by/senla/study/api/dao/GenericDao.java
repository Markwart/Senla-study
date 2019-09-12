package by.senla.study.api.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface GenericDao<T, PK> {

	T getByID(PK id, EntityManager entityManager);

	void update(T entity, EntityManager entityManager);

	void insert(T entity, EntityManager entityManager);

	void delete (T entity, EntityManager entityManager);

	List<T> selectAll(EntityManager entityManager);

	void merge(T entity, EntityManager entityManager);

	T getFullInfo(PK id, EntityManager entityManager);

}
