package by.senla.study.service.impl;

import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IUserAccountDao;
import by.senla.study.api.service.IUserAccountService;
import by.senla.study.model.entity.UserAccount;

@Service
public class UserAccountService extends AbstractService<UserAccount, Integer> implements IUserAccountService {

	
	private static IUserAccountDao userAccountDao;
	
	public UserAccountService() {
		super(UserAccount.class, userAccountDao);
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
