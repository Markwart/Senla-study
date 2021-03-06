package by.senla.study.board.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import by.senla.study.board.api.dao.GenericDao;

public abstract class AbstractDao<T, PK> implements GenericDao<T, PK> {

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> entityClass;

	protected AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public T getById(PK id) {
		return entityManager.find(getEntityClass(), id);
	}

	@Override
	public void insert(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteAll() {
		entityManager.createQuery(String.format("delete from %s", entityClass.getSimpleName())).executeUpdate();
	}

	@Override
	public List<T> selectAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> from = cq.from(getEntityClass());
		cq.select(from);
		return entityManager.createQuery(cq).getResultList();
	}

	protected T getSingleResult(TypedQuery<T> tq) {
		List<T> resultList = tq.getResultList();
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}
}
