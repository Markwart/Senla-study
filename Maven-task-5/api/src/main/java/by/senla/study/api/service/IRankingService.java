package by.senla.study.api.service;

import by.senla.study.model.entity.Ranking;

public interface IRankingService extends GenericService<Ranking, Integer> {
	
	Ranking getFullInfo(Integer id);
}
