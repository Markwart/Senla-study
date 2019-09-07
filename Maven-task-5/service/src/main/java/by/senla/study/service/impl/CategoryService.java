package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.service.ICategoryService;
import by.senla.study.dao.impl.CategoryDao;
import by.senla.study.model.entity.Ad;
import by.senla.study.model.entity.Category;

public class CategoryService implements ICategoryService {

	private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);

	private CategoryDao dao;

	public Category createEntity() {
		return new Category();
	}

	public Category get(Integer id) {
		Category entity = dao.get(id);
		return entity;
	}

	public void update(Category entity) {
		// TODO Auto-generated method stub

	}

	public void insert(Category entity) {
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public List<Category> selectAll() {
		List<Category> all = dao.selectAll();
		return all;
	}

}
