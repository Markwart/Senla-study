package by.senla.study.service.impl;

import java.util.List;

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
	public void mergeOperation(UserAccount entity) {
		userAccountDao.merge(entity, entityManager);		
	}
}
