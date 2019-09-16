package by.senla.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IRankingDao;
import by.senla.study.api.service.IRankingService;
import by.senla.study.model.entity.Ranking;

@Service
public class RankingService extends AbstractService<Ranking, Integer> implements IRankingService {

	private final IRankingDao rankingDao;

	@Autowired
	public RankingService(IRankingDao rankingDao) {
		super(Ranking.class, rankingDao);
		this.rankingDao = rankingDao;
	}
}
