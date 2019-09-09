package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IMessageDao;
import by.senla.study.api.service.IMessageService;
import by.senla.study.dao.impl.MessageDao;
import by.senla.study.model.entity.Message;

public class MessageService extends BaseService implements IMessageService {

	private static final Logger LOGGER = LogManager.getLogger(MessageService.class);
	private IMessageDao dao = MessageDao.getInstance();
	private static MessageService instance;

	private MessageService() {
	}

	public static MessageService getInstance() {
		if (instance == null) {
			instance = new MessageService();
		}
		return instance;
	}

	@Override
	public Message createEntity() {
		return new Message();
	}

	@Override
	public Message get(Integer id) {
		Message entity = dao.get(id, entityManager);
		return entity;
	}

	@Override
	public void update(Message entity) {
		entityManager.getTransaction().begin();
		try {
			dao.update(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format("message with id=%s was updated", entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, "EntityManager exception", e);
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void insert(Message entity) {
		entityManager.getTransaction().begin();
		try {
			dao.insert(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format("new message with id=%s was created", entity.getId()));

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

			LOGGER.log(Level.INFO, String.format("message with id=%s was deleted", id));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, "EntityManager exception", e);
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Message> selectAll() {
		List<Message> all = dao.selectAll(entityManager);
		return all;
	}

	@Override
	public Message getFullInfo(Integer id) {
		return dao.getFullInfo(id, entityManager);
	}
}
