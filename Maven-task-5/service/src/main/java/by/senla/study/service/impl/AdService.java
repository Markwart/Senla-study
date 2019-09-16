package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.api.dao.IAdDao;
import by.senla.study.api.service.IAdService;
import by.senla.study.model.entity.Ad;

@Service
@Transactional
public class AdService extends AbstractService<Ad, Integer> implements IAdService {

	private final IAdDao adDao;

	@Autowired
	public AdService(IAdDao adDao) {
		super(Ad.class, adDao);
		this.adDao = adDao;
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
