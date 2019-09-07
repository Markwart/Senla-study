package by.senla.study.dao.impl;

import by.senla.study.api.dao.IAdDao;
import by.senla.study.model.entity.Ad;

public class AdDao extends AbstractDao<Ad, Integer> implements IAdDao {

	private static AdDao instance;

	private AdDao() {
	}

	public static AdDao getInstance() {
		if (instance == null) {
			instance = new AdDao();
		}
		return instance;
	}
}
