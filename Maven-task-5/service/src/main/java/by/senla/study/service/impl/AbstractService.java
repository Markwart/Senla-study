package by.senla.study.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.cvs.module.processor.CsvReader;
import by.senla.cvs.module.processor.CsvWriter;
import by.senla.study.api.service.GenericService;
import by.senla.study.dao.utils.HibernateEntityManagerUtil;

public abstract class AbstractService<T, PK> implements GenericService<T, PK> {

	protected EntityManager entityManager = HibernateEntityManagerUtil.getEntityManager();
	protected final Logger LOGGER = LogManager.getLogger(getEntityClass());
	protected static final String EXCEPTION = "EntityManager exception";
	protected static final String FOLDER_CSV = "./data/";

	private static final String CREATED = "new %s with id=%d was created";
	private static final String UPDATED = "%s with id=%d was updated";
	private static final String DELETED = "%s with id=%d was deleted";
	private static final String MERGED = "%s with id=%d was merged";

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
	
	@Override
	public void merge(T entity) {
		transactionBegin();
		try {
			mergeOperation(entity);
			transactionCommit();

			LOGGER.log(Level.INFO, String.format(MERGED, getEntityClass().getSimpleName(), getPK(entity)));

		} catch (Exception e) {
			transactionRollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	public abstract void updateOperation(T entity);

	public abstract void insertOperation(T entity);

	public abstract void deleteOperation(PK id);
	
	public abstract void mergeOperation(T entity);

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
			throw new RuntimeException(e);
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
			throw new RuntimeException(e);
		}
		return (List<T>) objectList;
	}
}
