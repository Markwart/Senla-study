package by.senla.study.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.api.dao.IRankingDao;
import by.senla.study.api.service.IRankingService;
import by.senla.study.model.entity.Ranking;

@Service
@Transactional
public class RankingService extends AbstractService<Ranking, Integer> implements IRankingService {
	
	private final Logger LOGGER = LogManager.getLogger(getEntityClass());

	private final IRankingDao rankingDao;

	@Autowired
	public RankingService(IRankingDao rankingDao) {
		super(Ranking.class, rankingDao);
		this.rankingDao = rankingDao;
	}

	@Override
	public Double getRankByUserID(Integer id) {
		return rankingDao.getRankByUserID(id);
	}
}
