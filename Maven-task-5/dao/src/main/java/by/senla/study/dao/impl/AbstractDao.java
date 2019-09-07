package by.senla.study.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import by.senla.study.api.dao.IDao;

public abstract class AbstractDao<T, PK> implements IDao<T, PK> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> entityClass;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public T get(PK id) {
		return entityManager.find(getEntityClass(), id);
	}

	public void insert(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entity = entityManager.merge(entity);
		entityManager.flush();
	}

	public void delete(PK id) {
		entityManager.createQuery(String.format("delete from %s where id = %s", entityClass.getSimpleName(), id));
	}

	public List<T> selectAll() {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> from = cq.from(getEntityClass());
		cq.select(from);
		return em.createQuery(cq).getResultList();

	}
}
