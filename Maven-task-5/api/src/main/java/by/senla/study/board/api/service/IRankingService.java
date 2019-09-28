package by.senla.study.board.api.service;

import by.senla.study.board.model.entity.Ranking;

public interface IRankingService extends GenericService<Ranking, Integer> {

	Double getRankByUserID(Integer id);
	
}
