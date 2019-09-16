package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IAdDao;
import by.senla.study.api.service.IAdService;
import by.senla.study.model.entity.Ad;

@Service
public class AdService extends AbstractService<Ad, Integer> implements IAdService {

	private static IAdDao adDao;

	public AdService() {
		super(Ad.class, adDao);
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
		try {
			adList = adDao.searchByIndex(text);

		} catch (Exception e) {
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new ServiceException(EXCEPTION, e);
		}
		return adList;
	}
}
