package by.senla.study.board.api.service;

import by.senla.study.board.model.dto.RankingDto;
import by.senla.study.board.model.entity.Ranking;

public interface IRankingService extends GenericService<Ranking, Integer, RankingDto> {

	Double getTotalRankByUserId(Integer userId);
	
}
