package by.senla.study.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;

import by.senla.cvs.module.processor.CsvReader;
import by.senla.cvs.module.processor.CsvWriter;
import by.senla.study.api.dao.GenericDao;
import by.senla.study.api.service.GenericService;
import by.senla.study.dao.utils.HibernateEntityManagerUtil;

public abstract class AbstractService<T, PK> implements GenericService<T, PK> {

	protected EntityManager entityManager = HibernateEntityManagerUtil.getEntityManager();
	protected final Logger LOGGER = LogManager.getLogger(getEntityClass());
	protected static final String EXCEPTION = "Servie. Transaction exception";
	protected static final String FOLDER_CSV = "./data/";

	private static final String CREATED = "new %s with id=%d was created";
	private static final String UPDATED = "%s with id=%d was updated";
	private static final String DELETED = "%s with id=%d was deleted";
	private static final String MERGED = "%s with id=%d was merged";

	private Class<T> entityClass;

	private GenericDao<T, PK> dao;

	protected AbstractService(Class<T> entityClass, GenericDao<T, PK> dao) {
		this.entityClass = entityClass;
		this.dao = dao;
	}

	protected Class<T> getEntityClass() {
		return entityClass;
	}

	public GenericDao<T, PK> getDao() {
		return dao;
	}

	@Override
	public T getByID(PK id) {
		T entity = dao.getByID(id, entityManager);
		return entity;
	}

	@Override
	public List<T> selectAll() {
		List<T> userAccountList = dao.selectAll(entityManager);
		return userAccountList;
	}

	@Override
	public T getFullInfo(PK id) {
		return dao.getFullInfo(id, entityManager);
	}

	@Override
	public void update(T entity) {
		transactionBegin();
		try {
			dao.update(entity, entityManager);
			transactionCommit();

			LOGGER.log(Level.INFO, String.format(UPDATED, getEntityClass().getSimpleName(), getPK(entity)));

		} catch (Exception e) {
			transactionRollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new ServiceException(EXCEPTION, e);
		}
	}

	@Override
	public void insert(T entity) {
		transactionBegin();
		try {
			dao.insert(entity, entityManager);
			transactionCommit();

			LOGGER.log(Level.INFO, String.format(CREATED, getEntityClass().getSimpleName(), getPK(entity)));

		} catch (Exception e) {
			transactionRollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new ServiceException(EXCEPTION, e);
		}
	}

	@Override
	public void delete(T entity) {
		transactionBegin();
		try {
			dao.delete(entity, entityManager);
			transactionCommit();

			LOGGER.log(Level.INFO, String.format(DELETED, getEntityClass().getSimpleName(), getPK(entity)));

		} catch (Exception e) {
			transactionRollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new ServiceException(EXCEPTION, e);
		}
	}

	@Override
	public void merge(T entity) {
		transactionBegin();
		try {
			dao.merge(entity, entityManager);
			transactionCommit();

			LOGGER.log(Level.INFO, String.format(MERGED, getEntityClass().getSimpleName(), getPK(entity)));

		} catch (Exception e) {
			transactionRollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new ServiceException(EXCEPTION, e);
		}
	}

	protected abstract PK getPK(T entity);

	protected void transactionBegin() {
		entityManager.getTransaction().begin();
	}

	protected void transactionCommit() {
		entityManager.getTransaction().commit();
	}

	protected void transactionRollback() {
		entityManager.getTransaction().rollback();
	}

	@Override
	public void exportToCSV() {
		List<T> objectList = selectAll();
		List<Object> annotatedObjects = new ArrayList<>();

		for (T object : objectList) {
			annotatedObjects.add(object);
		}
		CsvWriter writer = new CsvWriter();
		try {
			writer.writeToCsv(annotatedObjects, FOLDER_CSV);
			LOGGER.log(Level.INFO, "Data was written to the file");
		} catch (IOException e) {
			LOGGER.log(Level.WARN, "Failed to write data to the file", e);
			throw new ServiceException(EXCEPTION, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> importFromCSV() {
		CsvReader reader = new CsvReader();
		List<Object> objectList;
		try {
			objectList = reader.readFromCsv(new File(FOLDER_CSV), entityClass);
			LOGGER.log(Level.INFO, "Data was successfully read from the file");
		} catch (IOException e) {
			LOGGER.log(Level.WARN, "Failed to read data from the file", e);
			throw new ServiceException(EXCEPTION, e);
		}
		return (List<T>) objectList;
	}
}
