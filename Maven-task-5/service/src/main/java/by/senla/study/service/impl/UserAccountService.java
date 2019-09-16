package by.senla.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IUserAccountDao;
import by.senla.study.api.service.IUserAccountService;
import by.senla.study.model.entity.UserAccount;

@Service
public class UserAccountService extends AbstractService<UserAccount, Integer> implements IUserAccountService {

	private final IUserAccountDao userAccountDao;

	@Autowired
	public UserAccountService(IUserAccountDao userAccountDao) {
		super(UserAccount.class, userAccountDao);
		this.userAccountDao = userAccountDao;
	}
}
