package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IChatDao;
import by.senla.study.api.service.IChatService;
import by.senla.study.dao.impl.ChatDao;
import by.senla.study.model.entity.Chat;

public class ChatService extends BaseService<Chat> implements IChatService {

	private static final Logger LOGGER = LogManager.getLogger(ChatService.class);
	private IChatDao dao = ChatDao.getInstance();
	private static ChatService instance;

	private ChatService() {
		super(Chat.class);
	}

	public static ChatService getInstance() {
		if (instance == null) {
			instance = new ChatService();
		}
		return instance;
	}

	@Override
	public Chat createEntity() {
		return new Chat();
	}

	@Override
	public Chat get(Integer id) {
		Chat entity = dao.get(id, entityManager);
		return entity;
	}

	@Override
	public void update(Chat entity) {
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
	public void insert(Chat entity) {
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
	public List<Chat> selectAll() {
		List<Chat> all = dao.selectAll(entityManager);
		return all;
	}

	@Override
	public Chat getFullInfo(Integer id) {
		return dao.getFullInfo(id, entityManager);
	}
}
