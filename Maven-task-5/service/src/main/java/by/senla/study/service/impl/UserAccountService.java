package by.senla.study.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import by.senla.cvs.module.processor.CsvWriter;
import by.senla.study.api.dao.IUserAccountDao;
import by.senla.study.api.service.IUserAccountService;
import by.senla.study.dao.impl.UserAccountDao;
import by.senla.study.model.entity.UserAccount;

public class UserAccountService extends AbstractService<UserAccount, Integer> implements IUserAccountService {

	private IUserAccountDao userAccountDao = UserAccountDao.getInstance();
	private static UserAccountService instance;

	private UserAccountService() {
		super(UserAccount.class);
	}

	public static UserAccountService getInstance() {
		if (instance == null) {
			instance = new UserAccountService();
		}
		return instance;
	}

	@Override
	public UserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	public UserAccount getByID(Integer id) {
		UserAccount entity = userAccountDao.getByID(id, entityManager);
		return entity;
	}

	@Override
	public List<UserAccount> selectAll() {
		List<UserAccount> userAccountList = userAccountDao.selectAll(entityManager);
		return userAccountList;
	}

	@Override
	public UserAccount getFullInfo(Integer id) {
		return userAccountDao.getFullInfo(id, entityManager);
	}

	@Override
	public void updateOperation(UserAccount entity) {
		userAccountDao.update(entity, entityManager);
	}

	@Override
	public void insertOperation(UserAccount entity) {
		userAccountDao.insert(entity, entityManager);
	}

	@Override
	public void deleteOperation(Integer id) {
		userAccountDao.deleteByID(id, entityManager);
	}

	@Override
	public Integer getPK(UserAccount entity) {
		return entity.getId();
	}

	@Override
	public void exportToCSV() {

		List<UserAccount> userAccountList = userAccountDao.selectAll(entityManager);
		List<Object> annotatedObjects = new ArrayList<>();
		File folder = new File("./data/");

		for (UserAccount user : userAccountList) {
			annotatedObjects.add(user);
		}
		CsvWriter writer = new CsvWriter();
		try {
			writer.writeToCsv(annotatedObjects);
		} catch (IOException e) {
			LOGGER.log(Level.WARN, "Failed to write", e);
			throw new RuntimeException(e);
		}
	}
}
