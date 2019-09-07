package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IRankingDao;
import by.senla.study.api.service.IRankingService;
import by.senla.study.dao.impl.RankingDao;
import by.senla.study.model.entity.Ranking;

public class RankingService implements IRankingService {

	private static final Logger LOGGER = LogManager.getLogger(RankingService.class);
	private IRankingDao dao = RankingDao.getInstance();
	private static RankingService instance;

	private RankingService() {
	}

	public static RankingService getInstance() {
		if (instance == null) {
			instance = new RankingService();
		}
		return instance;
	}

	public Ranking createEntity() {
		return new Ranking();
	}

	public Ranking get(Integer id) {
		Ranking entity = dao.get(id);
		return entity;
	}

	public void update(Ranking entity) {
		dao.update(entity);
	}

	public void insert(Ranking entity) {
		dao.insert(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public List<Ranking> selectAll() {
		List<Ranking> all = dao.selectAll();
		return all;
	}

}
