package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IAdDao;
import by.senla.study.api.service.IAdService;
import by.senla.study.dao.impl.AdDao;
import by.senla.study.model.entity.Ad;

public class AdService extends BaseService<Ad> implements IAdService {

	private static final Logger LOGGER = LogManager.getLogger(AdService.class);
	private IAdDao dao = AdDao.getInstance();
	private static AdService instance;

	private AdService() {
		super(Ad.class);
	}

	public static AdService getInstance() {
		if (instance == null) {
			instance = new AdService();
		}
		return instance;
	}

	@Override
	public Ad createEntity() {
		return new Ad();
	}

	@Override
	public Ad get(Integer id) {
		Ad entity = dao.get(id, entityManager);
		return entity;
	}

	@Override
	public void update(Ad entity) {
		entityManager.getTransaction().begin();
		try {
			dao.update(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(UPDATED, getEntityClassName(), entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(Ad entity) {
		entityManager.getTransaction().begin();
		try {
			dao.insert(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(CREATED, getEntityClassName(), entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Integer id) {
		entityManager.getTransaction().begin();
		try {
			dao.delete(id, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(DELETED, getEntityClassName(), id));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Ad> selectAll() {
		List<Ad> all = dao.selectAll(entityManager);
		return all;
	}

	@Override
	public Ad getFullInfo(Integer id) {
		return dao.getFullInfo(id, entityManager);
	}
}
