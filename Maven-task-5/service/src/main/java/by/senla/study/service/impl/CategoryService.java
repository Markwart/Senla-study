package by.senla.study.service.impl;

import org.springframework.stereotype.Service;

import by.senla.study.api.dao.ICategoryDao;
import by.senla.study.api.service.ICategoryService;
import by.senla.study.dao.impl.CategoryDao;
import by.senla.study.model.entity.Category;

@Service
public class CategoryService extends AbstractService<Category, Integer> implements ICategoryService {

	private static ICategoryDao categoryDao = CategoryDao.getInstance();
	private static CategoryService instance;

	private CategoryService() {
		super(Category.class, categoryDao);
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
	public Integer getPK(Category entity) {
		return entity.getId();
	}
}
