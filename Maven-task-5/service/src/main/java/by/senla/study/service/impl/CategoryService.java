package by.senla.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.senla.study.api.dao.ICategoryDao;
import by.senla.study.api.service.ICategoryService;
import by.senla.study.model.entity.Category;

@Service
public class CategoryService extends AbstractService<Category, Integer> implements ICategoryService {

	private final ICategoryDao categoryDao;

	@Autowired
	public CategoryService(ICategoryDao categoryDao) {
		super(Category.class, categoryDao);
		this.categoryDao = categoryDao;
	}
}
