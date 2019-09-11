package by.senla.study.service.impl;

import java.util.List;

import by.senla.study.api.dao.ICategoryDao;
import by.senla.study.api.service.ICategoryService;
import by.senla.study.dao.impl.CategoryDao;
import by.senla.study.model.entity.Category;

public class CategoryService extends AbstractService<Category, Integer> implements ICategoryService {

	private ICategoryDao categoryDao = CategoryDao.getInstance();
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
	public Category getByID(Integer id) {
		Category entity = categoryDao.getByID(id, entityManager);
		return entity;
	}

	@Override
	public List<Category> selectAll() {
		List<Category> categoryList = categoryDao.selectAll(entityManager);
		return categoryList;
	}

	@Override
	public Category getFullInfo(Integer id) {
		return categoryDao.getFullInfo(id, entityManager);
	}

	@Override
	public void updateOperation(Category entity) {
		categoryDao.update(entity, entityManager);
	}

	@Override
	public void insertOperation(Category entity) {
		categoryDao.insert(entity, entityManager);
	}

	@Override
	public void deleteOperation(Integer id) {
		categoryDao.deleteByID(id, entityManager);
	}

	@Override
	public Integer getPK(Category entity) {
		return entity.getId();
	}
}
