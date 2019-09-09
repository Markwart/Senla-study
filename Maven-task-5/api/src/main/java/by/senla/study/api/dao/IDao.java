package by.senla.study.api.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface IDao<T, PK> {

	T get(PK id, EntityManager entityManager);

	void update(T entity, EntityManager entityManager);

	void insert(T entity, EntityManager entityManager);

	void delete(PK id, EntityManager entityManager);

	List<T> selectAll(EntityManager entityManager);

}
