package by.senla.study.board.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.IRankingDao;
import by.senla.study.board.api.service.IRankingService;
import by.senla.study.board.model.dto.RankingDto;
import by.senla.study.board.model.entity.Ranking;

@Service
@Transactional
public class RankingService extends AbstractService<Ranking, Integer, RankingDto> implements IRankingService {

	private static final Logger LOGGER = LogManager.getLogger(RankingService.class);

	private final IRankingDao rankingDao;

	@Autowired
	public RankingService(IRankingDao rankingDao) {
		super(Ranking.class, rankingDao);
		this.rankingDao = rankingDao;
	}

	@Override
	public Double getRankByUserID(Integer userId) {
		return rankingDao.getRankByUserID(userId);
	}

	@Override
	public void setFieldsAndUpdate(Ranking entity, RankingDto dto) {
		if (dto.getFeedback() != null)
			entity.setFeedback(dto.getFeedback());
		update(entity);
	}
}
