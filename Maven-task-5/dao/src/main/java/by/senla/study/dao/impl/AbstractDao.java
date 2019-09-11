package by.senla.study.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import by.senla.study.api.dao.GenericDao;

public abstract class AbstractDao<T, PK> implements GenericDao<T, PK> {

	private Class<T> entityClass;

	protected AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public T getByID(PK id, EntityManager entityManager) {
		return entityManager.find(getEntityClass(), id);
	}

	@Override
	public void insert(T entity, EntityManager entityManager) {
		entityManager.persist(entity);
	}

	@Override
	public void update(T entity, EntityManager entityManager) {
		entityManager.merge(entity);
	}

	@Override
	public void deleteByID(PK id, EntityManager entityManager) {
		entityManager.createQuery(String.format("delete from %s where id = %s", entityClass.getSimpleName(), id));
	}

	@Override
	public List<T> selectAll(EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> from = cq.from(getEntityClass());
		cq.select(from);
		return em.createQuery(cq).getResultList();
	}

	protected T getSingleResult(TypedQuery<T> tq) {
		List<T> resultList = tq.getResultList();
		if (resultList.size() != 1) {
			throw new IllegalArgumentException("unexpected result count:" + resultList.size());
		}
		return resultList.get(0);
	}
}
