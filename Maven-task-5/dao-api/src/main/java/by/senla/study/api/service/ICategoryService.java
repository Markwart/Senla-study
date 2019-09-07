package by.senla.study.api.service;

import java.util.List;

import by.senla.study.model.entity.Category;

public interface ICategoryService {

	Category createEntity();

	Category get(Integer id);
	
	void update(Category entity);

	void insert(Category entity);

	void delete(Integer id);
	
	List<Category> selectAll();
}
