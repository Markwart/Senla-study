package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IAdDao;
import by.senla.study.api.service.IAdService;
import by.senla.study.dao.impl.AdDao;
import by.senla.study.model.entity.Ad;

public class AdService implements IAdService {

	private static final Logger LOGGER = LogManager.getLogger(AdService.class);
	private IAdDao dao = AdDao.getInstance();
	private static AdService instance;

	private AdService() {
	}

	public static AdService getInstance() {
		if (instance == null) {
			instance = new AdService();
		}
		return instance;
	}

	public Ad createEntity() {
		return new Ad();
	}

	public Ad get(Integer id) {
		Ad entity = dao.get(id);
		return entity;
	}

	public void update(Ad entity) {
		dao.update(entity);
	}

	public void insert(Ad entity) {
		dao.insert(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public List<Ad> selectAll() {
		List<Ad> all = dao.selectAll();
		return all;
	}

}
