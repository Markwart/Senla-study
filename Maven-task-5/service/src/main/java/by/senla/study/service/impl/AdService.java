package by.senla.study.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.api.dao.IAdDao;
import by.senla.study.api.service.IAdService;
import by.senla.study.model.entity.Ad;
import by.senla.study.model.enums.Status;

@Service
@Transactional
public class AdService extends AbstractService<Ad, Integer> implements IAdService {

	private final Logger LOGGER = LogManager.getLogger(getEntityClass());
	
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

	@Override
	public List<Ad> sellerHistory(Integer sellerID) {
		return adDao.sellerHistory(sellerID);
	}

	@Override
	public List<Ad> findAdsByCategory(String category, String column, Boolean ascending) {
		return adDao.findAdsByCategory(category, column, ascending);
	}

	@Override
	public void moveAdOnTop (Integer id) {
		Ad entity = dao.getByID(id);
		entity.setStatus(Status.OPEN_VIP);
		entity.setUpdated(new Date());
		dao.update(entity);
		LOGGER.log(Level.INFO, String.format(UPDATED, getEntityClass().getSimpleName(), entity.getId()));
	}
	
}
