package by.senla.study.board.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.GenericDao;
import by.senla.study.board.api.service.GenericService;
import by.senla.study.board.model.dto.BaseDto;
import by.senla.study.board.model.entity.BaseEntity;

@Transactional
public abstract class AbstractService<T extends BaseEntity, PK, D extends BaseDto> implements GenericService<T, PK, D> {

	private static final Logger LOGGER = LogManager.getLogger(AbstractService.class);
	protected static final String EXCEPTION = "Service layer. Transaction exception";

	protected static final String CREATED = "new %s with id=%d was created";
	protected static final String UPDATED = "%s with id=%d was updated";
	private static final String DELETED = "%s with id=%d was deleted";
	private static final String DELETED_ALL = "all entities was deleted";

	private Class<T> entityClass;

	private GenericDao<T, PK> dao;

	protected AbstractService(Class<T> entityClass, GenericDao<T, PK> dao) {
		this.entityClass = entityClass;
		this.dao = dao;
	}

	protected Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public T getById(PK id) {
		T entity = dao.getById(id);
		return entity;
	}

	@Override
	public List<T> selectAll() {
		return dao.selectAll();
	}

	@Override
	public T getFullInfo(PK id) {
		return dao.getFullInfo(id);
	}

	@Override
	public void update(T entity) {
		try {
			entity.setUpdated(new Date());
			dao.update(entity);
			LOGGER.log(Level.INFO, String.format(UPDATED, getEntityClass().getSimpleName(), entity.getId()));

		} catch (Exception e) {
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new ServiceException(EXCEPTION, e);
		}
	}

	@Override
	public void insert(T entity) {
		Date current = new Date();
		try {
			entity.setCreated(current);
			entity.setUpdated(current);
			dao.insert(entity);
			LOGGER.log(Level.INFO, String.format(CREATED, getEntityClass().getSimpleName(), entity.getId()));

		} catch (Exception e) {
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new ServiceException(EXCEPTION, e);
		}
	}

	@Override
	public void deleteById(PK id) {
		try {
			T entity = dao.getById(id);
			dao.delete(entity);
			LOGGER.log(Level.INFO, String.format(DELETED, getEntityClass().getSimpleName(), id));

		} catch (Exception e) {
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new ServiceException(EXCEPTION, e);
		}
	}

	@Override
	public void deleteAll() {
		try {
			dao.deleteAll();
			LOGGER.log(Level.INFO, DELETED_ALL);

		} catch (Exception e) {
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new ServiceException(EXCEPTION, e);
		}
	}
}
