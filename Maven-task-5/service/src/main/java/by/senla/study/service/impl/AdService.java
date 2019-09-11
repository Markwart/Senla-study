package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;

import by.senla.study.api.dao.IAdDao;
import by.senla.study.api.service.IAdService;
import by.senla.study.dao.impl.AdDao;
import by.senla.study.model.entity.Ad;

public class AdService extends AbstractService<Ad, Integer> implements IAdService {

	private IAdDao adDao = AdDao.getInstance();
	private static AdService instance;

	private AdService() {
		super(Ad.class);
	}

	public static AdService getInstance() {
		if (instance == null) {
			instance = new AdService();
		}
		return instance;
	}

	@Override
	public Ad createEntity() {
		return new Ad();
	}

	@Override
	public Ad getByID(Integer id) {
		Ad entity = adDao.getByID(id, entityManager);
		return entity;
	}

	@Override
	public List<Ad> selectAll() {
		List<Ad> adList = adDao.selectAll(entityManager);
		return adList;
	}

	@Override
	public Ad getFullInfo(Integer id) {
		return adDao.getFullInfo(id, entityManager);
	}

	@Override
	public void updateOperation(Ad entity) {
		adDao.update(entity, entityManager);
	}

	@Override
	public void insertOperation(Ad entity) {
		adDao.insert(entity, entityManager);
	}

	@Override
	public void deleteOperation(Integer id) {
		adDao.deleteByID(id, entityManager);
	}

	@Override
	public Integer getPK(Ad entity) {
		return entity.getId();
	}
	
	@Override
	public void mergeOperation(Ad entity) {
		adDao.merge(entity, entityManager);
	}

	@Override
	public List<Ad> searchByIndex(String text) {
		List<Ad> adList;
		transactionBegin();
		try {
			adList = adDao.searchByIndex(text, entityManager);
			transactionCommit();

		} catch (Exception e) {
			transactionRollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
		return adList;
	}
}
