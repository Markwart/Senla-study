package by.senla.study.board.api.dao;

import by.senla.study.board.model.entity.Ranking;

public interface IRankingDao extends GenericDao<Ranking, Integer> {

	Double getTotalRankByUserId(Integer id);

}
