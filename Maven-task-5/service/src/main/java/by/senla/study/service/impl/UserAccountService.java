package by.senla.study.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.api.dao.IUserAccountDao;
import by.senla.study.api.service.IUserAccountService;
import by.senla.study.model.entity.UserAccount;

@Service
@Transactional
public class UserAccountService extends AbstractService<UserAccount, Integer> implements IUserAccountService {
	
	private final Logger LOGGER = LogManager.getLogger(getEntityClass());

	private final IUserAccountDao userAccountDao;

	@Autowired
	public UserAccountService(IUserAccountDao userAccountDao) {
		super(UserAccount.class, userAccountDao);
		this.userAccountDao = userAccountDao;
	}
}
