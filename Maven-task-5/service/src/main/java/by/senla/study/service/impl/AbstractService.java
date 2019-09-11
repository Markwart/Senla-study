package by.senla.study.service.impl;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.service.GenericService;
import by.senla.study.dao.utils.HibernateEntityManagerUtil;

public abstract class AbstractService<T, PK> implements GenericService<T, PK> {

	protected EntityManager entityManager = HibernateEntityManagerUtil.getEntityManager();
	protected final Logger LOGGER = LogManager.getLogger(getEntityClass());
	protected static final String EXCEPTION = "EntityManager exception";

	private static final String CREATED = "new %s with id=%d was created";
	private static final String UPDATED = "%s with id=%d was updated";
	private static final String DELETED = "%s with id=%d was deleted";

	private Class<T> entityClass;

	protected AbstractService(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public void update(T entity) {
		transactionBegin();
		try {
			updateOperation(entity);
			transactionCommit();

			LOGGER.log(Level.INFO, String.format(UPDATED, getEntityClass().getSimpleName(), getPK(entity)));

		} catch (Exception e) {
			transactionRollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(T entity) {
		transactionBegin();
		try {
			insertOperation(entity);
			transactionCommit();

			LOGGER.log(Level.INFO, String.format(CREATED, getEntityClass().getSimpleName(), getPK(entity)));

		} catch (Exception e) {
			transactionRollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteByID(PK id) {
		transactionBegin();
		try {
			deleteOperation(id);
			transactionCommit();

			LOGGER.log(Level.INFO, String.format(DELETED, getEntityClass().getSimpleName(), id));

		} catch (Exception e) {
			transactionRollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	public abstract void updateOperation(T entity);

	public abstract void insertOperation(T entity);

	public abstract void deleteOperation(PK id);

	public abstract PK getPK(T entity);

	public void transactionBegin() {
		entityManager.getTransaction().begin();
	}

	public void transactionCommit() {
		entityManager.getTransaction().commit();
	}

	public void transactionRollback() {
		entityManager.getTransaction().rollback();
	}
}
