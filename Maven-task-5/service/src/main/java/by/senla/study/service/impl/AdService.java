package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IAdDao;
import by.senla.study.api.service.IAdService;
import by.senla.study.model.entity.Ad;

@Service
public class AdService extends AbstractService<Ad, Integer> implements IAdService {

	@Autowired
	private static IAdDao adDao/* = AdDao.getInstance()*/;
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
	public Integer getPK(Ad entity) {
		return entity.getId();
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
