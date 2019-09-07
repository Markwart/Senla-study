package by.senla.study.dao.impl;

import by.senla.study.api.dao.IUserAccountDao;
import by.senla.study.model.entity.UserAccount;

public class UserAccountDao extends AbstractDao<UserAccount, Integer> implements IUserAccountDao {

	private static UserAccountDao instance;

	private UserAccountDao() {
	}

	public static UserAccountDao getInstance() {
		if (instance == null) {
			instance = new UserAccountDao();
		}
		return instance;
	}
}
