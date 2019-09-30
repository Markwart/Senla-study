package by.senla.study.board.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.ICategoryDao;
import by.senla.study.board.api.service.ICategoryService;
import by.senla.study.board.model.entity.Category;

@Service
@Transactional
public class CategoryService extends AbstractService<Category, Integer> implements ICategoryService {
	
	private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);

	private final ICategoryDao categoryDao;

	@Autowired
	public CategoryService(ICategoryDao categoryDao) {
		super(Category.class, categoryDao);
		this.categoryDao = categoryDao;
	}
}
