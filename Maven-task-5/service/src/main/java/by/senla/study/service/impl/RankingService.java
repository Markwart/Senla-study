package by.senla.study.service.impl;

import java.util.Date;

import by.senla.study.api.dao.IRankingDao;
import by.senla.study.api.service.IRankingService;
import by.senla.study.dao.impl.RankingDao;
import by.senla.study.model.entity.Ranking;

public class RankingService extends AbstractService<Ranking, Integer> implements IRankingService {

	private static IRankingDao rankingDao = RankingDao.getInstance();
	private static RankingService instance;

	private RankingService() {
		super(Ranking.class, rankingDao);
	}

	public static RankingService getInstance() {
		if (instance == null) {
			instance = new RankingService();
		}
		return instance;
	}

	@Override
	public Ranking createEntity() {
		return new Ranking();
	}

	@Override
	public Ranking updateOperation(Ranking entity) {
		entity.setUpdated(new Date());
		return entity;
	}

	@Override
	public Ranking insertOperation(Ranking entity) {
		entity.setCreated(new Date());
		return entity;
	}

	@Override
	public Integer getPK(Ranking entity) {
		return entity.getId();
	}

	@Override
	public Ranking mergeOperation(Ranking entity) {
		entity.setUpdated(new Date());
		return entity;
	}
}
