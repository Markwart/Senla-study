package by.senla.study.dao.impl;

import by.senla.study.api.dao.ICategoryDao;
import by.senla.study.model.entity.Category;

public class CategoryDao extends AbstractDao<Category, Integer> implements ICategoryDao {

	private static CategoryDao instance;

	private CategoryDao() {
	}

	public static CategoryDao getInstance() {
		if (instance == null) {
			instance = new CategoryDao();
		}
		return instance;
	}
}
