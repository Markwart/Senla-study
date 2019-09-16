package by.senla.study.service.impl;

import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IRankingDao;
import by.senla.study.api.service.IRankingService;
import by.senla.study.model.entity.Ranking;

@Service
public class RankingService extends AbstractService<Ranking, Integer> implements IRankingService {

	private static IRankingDao rankingDao;

	private RankingService() {
		super(Ranking.class, rankingDao);
	}

	@Override
	public Ranking createEntity() {
		return new Ranking();
	}

	@Override
	public Integer getPK(Ranking entity) {
		return entity.getId();
	}
}
