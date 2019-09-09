package by.senla.study.service.impl;

import javax.persistence.EntityManager;

import by.senla.study.dao.utils.HibernateEntityManagerUtil;

public class BaseService<T> {

	protected EntityManager entityManager = HibernateEntityManagerUtil.getEntityManager();

	protected static final String EXCEPTION = "EntityManager exception";

	protected static final String CREATED = "new %s with id=%d was created";
	protected static final String UPDATED = "%s with id=%d was updated";
	protected static final String DELETED = "%s with id=%d was deleted";

	private Class<T> entityClass;

	protected BaseService(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected String getEntityClassName() {
		return entityClass.getSimpleName();
	}
}
