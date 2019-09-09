package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IChatDao;
import by.senla.study.api.service.IChatService;
import by.senla.study.dao.impl.ChatDao;
import by.senla.study.model.entity.Chat;

public class ChatService extends BaseService implements IChatService {

	private static final Logger LOGGER = LogManager.getLogger(ChatService.class);
	private IChatDao dao = ChatDao.getInstance();
	private static ChatService instance;

	private ChatService() {
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

			LOGGER.log(Level.INFO, String.format("chat with id=%s was updated", entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, "EntityManager exception", e);
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void insert(Chat entity) {
		entityManager.getTransaction().begin();
		try {
			dao.insert(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format("new chat with id=%s was created", entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, "EntityManager exception", e);
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void delete(Integer id) {
		entityManager.getTransaction().begin();
		try {
			dao.delete(id, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format("chat with id=%s was deleted", id));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, "EntityManager exception", e);
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
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
