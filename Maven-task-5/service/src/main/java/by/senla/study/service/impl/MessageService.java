package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IMessageDao;
import by.senla.study.api.service.IMessageService;
import by.senla.study.dao.impl.MessageDao;
import by.senla.study.model.entity.Message;

public class MessageService implements IMessageService {

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

	public Message createEntity() {
		return new Message();
	}

	public Message get(Integer id) {
		Message entity = dao.get(id);
		return entity;
	}

	public void update(Message entity) {
		dao.update(entity);
	}

	public void insert(Message entity) {
		dao.insert(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public List<Message> selectAll() {
		List<Message> all = dao.selectAll();
		return all;
	}

}
