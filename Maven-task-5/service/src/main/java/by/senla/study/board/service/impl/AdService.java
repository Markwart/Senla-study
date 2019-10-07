package by.senla.study.board.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.IAdDao;
import by.senla.study.board.api.dao.ICategoryDao;
import by.senla.study.board.api.service.IAdService;
import by.senla.study.board.model.dto.AdDto;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.enums.Status;
import by.senla.study.board.model.search.AdSearchDto;

@Service
@Transactional
public class AdService extends AbstractService<Ad, Integer, AdDto> implements IAdService {

	private static final Logger LOGGER = LogManager.getLogger(AdService.class);

	private final IAdDao adDao;
	private final ICategoryDao categoryDao;

	@Autowired
	public AdService(IAdDao adDao, ICategoryDao categoryDao) {
		super(Ad.class, adDao);
		this.adDao = adDao;
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Ad> searchByIndex(String keyword) {
		List<Ad> adList;
		try {
			adList = adDao.searchByIndex(keyword);

		} catch (Exception e) {
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new ServiceException(EXCEPTION, e);
		}
		return adList;
	}

	@Override
	public List<Ad> sellerHistory(Integer sellerId) {
		return adDao.sellerHistory(sellerId);
	}

	@Override
	public List<Ad> findAdsByCategory(AdSearchDto dto) {
		return adDao.findAdsByCategory(dto);
	}

	@Override
	public void moveAdOnTop(Integer adId) {
		Ad entity = dao.getById(adId);
		entity.setStatus(Status.OPEN_VIP);
		update(entity);
	}

	@Override
	public void closeAd(Integer adId) {
		Ad entity = dao.getById(adId);
		entity.setStatus(Status.CLOSED);
		update(entity);
	}

	@Override
	public void setFieldsAndUpdate(Ad entity, AdDto dto) {
		if (dto.getImage() != null)
			entity.setImage(dto.getImage());
		if (dto.getPrice() != null)
			entity.setPrice(dto.getPrice());
		if (dto.getStatus() != null)
			entity.setStatus(dto.getStatus());
		if (dto.getText() != null)
			entity.setText(dto.getText());
		if (dto.getTheme() != null)
			entity.setTheme(dto.getTheme());
		if (dto.getCategoryId() != null)
			entity.setCategory(categoryDao.getById(dto.getCategoryId()));
		update(entity);
	}
}
