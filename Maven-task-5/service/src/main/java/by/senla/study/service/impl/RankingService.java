package by.senla.study.service.impl;

import java.util.Date;
import java.util.List;

import by.senla.study.api.dao.IRankingDao;
import by.senla.study.api.service.IRankingService;
import by.senla.study.dao.impl.RankingDao;
import by.senla.study.model.entity.Ranking;

public class RankingService extends AbstractService<Ranking, Integer> implements IRankingService {

	private IRankingDao rankingDao = RankingDao.getInstance();
	private static RankingService instance;

	private RankingService() {
		super(Ranking.class);
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
	public Ranking getByID(Integer id) {
		Ranking entity = rankingDao.getByID(id, entityManager);
		return entity;
	}

	@Override
	public List<Ranking> selectAll() {
		List<Ranking> rankingList = rankingDao.selectAll(entityManager);
		return rankingList;
	}

	@Override
	public Ranking getFullInfo(Integer id) {
		return rankingDao.getFullInfo(id, entityManager);
	}

	@Override
	public void updateOperation(Ranking entity) {
		entity.setUpdated(new Date());
		rankingDao.update(entity, entityManager);
	}

	@Override
	public void insertOperation(Ranking entity) {
		entity.setCreated(new Date());
		rankingDao.insert(entity, entityManager);
	}

	@Override
	public void deleteOperation(Integer id) {
		rankingDao.deleteByID(id, entityManager);
	}

	@Override
	public Integer getPK(Ranking entity) {
		return entity.getId();
	}

	@Override
	public void mergeOperation(Ranking entity) {
		entity.setUpdated(new Date());
		rankingDao.merge(entity, entityManager);		
	}
}
