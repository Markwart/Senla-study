package by.senla.study.api.service;

import java.util.List;

import by.senla.study.model.entity.Ad;

public interface IAdService {

	Ad createEntity();

	Ad get(Integer id);
	
	void update(Ad entity);

	void insert(Ad entity);

	void delete(Integer id);
	
	List<Ad> selectAll();

	Ad getFullInfo(Integer id);
}
