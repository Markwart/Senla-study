package by.senla.study.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import by.senla.study.api.dao.IDao;
import by.senla.study.dao.utils.HibernateEntityManagerUtil;

public abstract class AbstractDao<T, PK> implements IDao<T, PK> {

	private EntityManager entityManager = HibernateEntityManagerUtil.getEntityManager();

	private Class<T> entityClass;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public T get(PK id) {
		return entityManager.find(getEntityClass(), id);
	}

	public void insert(T entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	public void update(T entity) {
		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);
		entityManager.flush();
		entityManager.getTransaction().commit();
	}

	public void delete(PK id) {
		entityManager.createQuery(String.format("delete from %s where id = %s", entityClass.getSimpleName(), id));
	}

	public List<T> selectAll() {
		EntityManager em = entityManager;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> from = cq.from(getEntityClass());
		cq.select(from);
		return em.createQuery(cq).getResultList();

	}
}
