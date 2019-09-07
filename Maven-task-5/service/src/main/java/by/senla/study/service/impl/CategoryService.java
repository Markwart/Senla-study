package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.ICategoryDao;
import by.senla.study.api.service.ICategoryService;
import by.senla.study.dao.impl.CategoryDao;
import by.senla.study.model.entity.Category;

public class CategoryService implements ICategoryService {

	private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);
	private ICategoryDao dao = CategoryDao.getInstance();
	private static CategoryService instance;

	private CategoryService() {
	}

	public static CategoryService getInstance() {
		if (instance == null) {
			instance = new CategoryService();
		}
		return instance;
	}

	public Category createEntity() {
		return new Category();
	}

	public Category get(Integer id) {
		Category entity = dao.get(id);
		return entity;
	}

	public void update(Category entity) {
		dao.update(entity);
	}

	public void insert(Category entity) {
		dao.insert(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public List<Category> selectAll() {
		List<Category> all = dao.selectAll();
		return all;
	}
}
