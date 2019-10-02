package by.senla.study.board.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.IAdDao;
import by.senla.study.board.api.dao.IPersonalDataDao;
import by.senla.study.board.api.dao.IUserAccountDao;
import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.model.entity.UserAccount;

@Service
@Transactional
public class UserAccountService extends AbstractService<UserAccount, Integer> implements IUserAccountService {

	private static final Logger LOGGER = LogManager.getLogger(UserAccountService.class);

	private final IUserAccountDao userAccountDao;
	private final IPersonalDataDao personalDataDao;
	private final IAdDao adDao;

	@Autowired
	public UserAccountService(IUserAccountDao userAccountDao, IPersonalDataDao personalDataDao, IAdDao adDao) {
		super(UserAccount.class, userAccountDao);
		this.userAccountDao = userAccountDao;
		this.personalDataDao = personalDataDao;
		this.adDao = adDao;
	}

	@Override
	public void addToWishList(Integer adId, Integer userId) {
		UserAccount userAccount = userAccountDao.getById(userId);
		userAccount.getWishlist().add(adDao.getById(adId));
		update(userAccount);
	}
}
