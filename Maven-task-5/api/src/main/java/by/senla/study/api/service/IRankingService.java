package by.senla.study.api.service;

import java.util.List;

import by.senla.study.model.entity.Ranking;

public interface IRankingService {
	
	Ranking createEntity();

	Ranking get(Integer id);
	
	void update(Ranking entity);

	void insert(Ranking entity);

	void delete(Integer id);
	
	List<Ranking> selectAll();
}
