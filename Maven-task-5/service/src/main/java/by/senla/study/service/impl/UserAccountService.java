package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IUserAccountDao;
import by.senla.study.api.service.IUserAccountService;
import by.senla.study.dao.impl.UserAccountDao;
import by.senla.study.model.entity.UserAccount;

public class UserAccountService implements IUserAccountService {

	private static final Logger LOGGER = LogManager.getLogger(UserAccountService.class);
	private IUserAccountDao dao = UserAccountDao.getInstance();
	private static UserAccountService instance;

	private UserAccountService() {
	}

	public static UserAccountService getInstance() {
		if (instance == null) {
			instance = new UserAccountService();
		}
		return instance;
	}

	public UserAccount createEntity() {
		return new UserAccount();
	}

	public UserAccount get(Integer id) {
		UserAccount entity = dao.get(id);
		return entity;
	}

	public void update(UserAccount entity) {
		dao.update(entity);
	}

	public void insert(UserAccount entity) {
		dao.insert(entity);
		LOGGER.log(Level.INFO, String.format("new user created with id=%s:", entity.getId()), entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public List<UserAccount> selectAll() {
		List<UserAccount> all = dao.selectAll();
		return all;
	}

}
