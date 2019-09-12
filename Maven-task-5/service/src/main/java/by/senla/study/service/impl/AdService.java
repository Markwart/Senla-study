package by.senla.study.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.hibernate.service.spi.ServiceException;

import by.senla.study.api.dao.IAdDao;
import by.senla.study.api.service.IAdService;
import by.senla.study.dao.impl.AdDao;
import by.senla.study.model.entity.Ad;
import by.senla.study.model.enums.Status;

public class AdService extends AbstractService<Ad, Integer> implements IAdService {

	private static IAdDao adDao = AdDao.getInstance();
	private static AdService instance;

	private AdService() {
		super(Ad.class, adDao);
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
	public Ad updateOperation(Ad entity) {
		entity.setUpdated(new Date());
		return entity;
	}

	@Override
	public Ad insertOperation(Ad entity) {
		entity.setCreated(new Date());
		entity.setStatus(Status.OPEN);
		return entity;
	}

	@Override
	public Integer getPK(Ad entity) {
		return entity.getId();
	}
	
	@Override
	public Ad mergeOperation(Ad entity) {
		entity.setUpdated(new Date());
		return entity;
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
			throw new ServiceException(EXCEPTION, e);
		}
		return adList;
	}
}
