package by.senla.study.service.impl;

import by.senla.study.api.dao.IUserAccountDao;
import by.senla.study.api.service.IUserAccountService;
import by.senla.study.dao.impl.UserAccountDao;
import by.senla.study.model.entity.UserAccount;

public class UserAccountService extends AbstractService<UserAccount, Integer> implements IUserAccountService {

	private static IUserAccountDao userAccountDao = UserAccountDao.getInstance();
	private static UserAccountService instance;

	private UserAccountService() {
		super(UserAccount.class, userAccountDao);
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
	public Integer getPK(UserAccount entity) {
		return entity.getId();
	}
}
