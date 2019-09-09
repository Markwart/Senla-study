package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IRankingDao;
import by.senla.study.api.service.IRankingService;
import by.senla.study.dao.impl.RankingDao;
import by.senla.study.model.entity.Ranking;

public class RankingService extends BaseService<Ranking> implements IRankingService {

	private static final Logger LOGGER = LogManager.getLogger(RankingService.class);
	private IRankingDao dao = RankingDao.getInstance();
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
	public Ranking get(Integer id) {
		Ranking entity = dao.get(id, entityManager);
		return entity;
	}

	@Override
	public void update(Ranking entity) {
		entityManager.getTransaction().begin();
		try {
			dao.update(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(UPDATED, getEntityClassName(), entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		} 
	}

	@Override
	public void insert(Ranking entity) {
		entityManager.getTransaction().begin();
		try {
			dao.insert(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(CREATED, getEntityClassName(), entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		} 
	}

	@Override
	public void delete(Integer id) {
		entityManager.getTransaction().begin();
		try {
			dao.delete(id, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(DELETED, getEntityClassName(), id));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		} 
	}

	@Override
	public List<Ranking> selectAll() {
		List<Ranking> all = dao.selectAll(entityManager);
		return all;
	}

	@Override
	public Ranking getFullInfo(Integer id) {
		return dao.getFullInfo(id, entityManager);
	}
}
