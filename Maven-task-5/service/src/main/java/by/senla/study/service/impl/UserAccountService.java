package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IUserAccountDao;
import by.senla.study.api.service.IUserAccountService;
import by.senla.study.dao.impl.UserAccountDao;
import by.senla.study.model.entity.UserAccount;

public class UserAccountService extends BaseService<UserAccount> implements IUserAccountService {

	private static final Logger LOGGER = LogManager.getLogger(UserAccountService.class);
	private IUserAccountDao dao = UserAccountDao.getInstance();
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
	public UserAccount get(Integer id) {
		UserAccount entity = dao.get(id, entityManager);
		return entity;
	}

	@Override
	public void update(UserAccount entity) {
		entityManager.getTransaction().begin();
		try {
			dao.update(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(UPDATED, getEntityClassName(), entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(UserAccount entity) {
		entityManager.getTransaction().begin();
		try {
			dao.insert(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(CREATED, getEntityClassName(), entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Integer id) {
		entityManager.getTransaction().begin();
		try {
			dao.delete(id, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(DELETED, getEntityClassName(), id));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<UserAccount> selectAll() {
		List<UserAccount> all = dao.selectAll(entityManager);
		return all;
	}

	@Override
	public UserAccount getFullInfo(Integer id) {
		return dao.getFullInfo(id, entityManager);
	}
}
