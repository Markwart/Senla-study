package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.ICategoryDao;
import by.senla.study.api.service.ICategoryService;
import by.senla.study.dao.impl.CategoryDao;
import by.senla.study.model.entity.Category;

public class CategoryService extends BaseService<Category> implements ICategoryService {

	private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);
	private ICategoryDao dao = CategoryDao.getInstance();
	private static CategoryService instance;

	private CategoryService() {
		super(Category.class);
	}

	public static CategoryService getInstance() {
		if (instance == null) {
			instance = new CategoryService();
		}
		return instance;
	}

	@Override
	public Category createEntity() {
		return new Category();
	}

	@Override
	public Category get(Integer id) {
		Category entity = dao.get(id, entityManager);
		return entity;
	}

	@Override
	public void update(Category entity) {
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
	public void insert(Category entity) {
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
	public List<Category> selectAll() {
		List<Category> all = dao.selectAll(entityManager);
		return all;
	}

	@Override
	public Category getFullInfo(Integer id) {
		return dao.getFullInfo(id, entityManager);
	}
}
