package by.senla.study.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import by.senla.study.api.dao.IDao;

public abstract class AbstractDao<T, PK> implements IDao<T, PK> {

	private Class<T> entityClass;

	protected AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public T get(PK id, EntityManager entityManager) {
		return entityManager.find(getEntityClass(), id);
	}

	@Override
	public void insert(T entity, EntityManager entityManager) {
		entityManager.persist(entity);
	}

	@Override
	public void update(T entity, EntityManager entityManager) {
		entity = entityManager.merge(entity);
		entityManager.flush();
	}

	@Override
	public void delete(PK id, EntityManager entityManager) {
		entityManager.remove(get(id, entityManager));
		entityManager.flush();
	}

	@Override
	public List<T> selectAll(EntityManager entityManager) {
		EntityManager em = entityManager;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> from = cq.from(getEntityClass());
		cq.select(from);
		return em.createQuery(cq).getResultList();
	}
}
