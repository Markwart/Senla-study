package by.senla.study.dao.impl;

import by.senla.study.api.dao.IRankingDao;
import by.senla.study.model.entity.Ranking;

public class RankingDao extends AbstractDao<Ranking, Integer> implements IRankingDao {

	private static RankingDao instance;

	private RankingDao() {
	}

	public static RankingDao getInstance() {
		if (instance == null) {
			instance = new RankingDao();
		}
		return instance;
	}
}
