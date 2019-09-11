package by.senla.study.service.impl;

import java.util.List;

import by.senla.study.api.dao.IMessageDao;
import by.senla.study.api.service.IMessageService;
import by.senla.study.dao.impl.MessageDao;
import by.senla.study.model.entity.Message;

public class MessageService extends AbstractService<Message, Integer> implements IMessageService {

	private IMessageDao messageDao = MessageDao.getInstance();
	private static MessageService instance;

	private MessageService() {
		super(Message.class);
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
	public Message getByID(Integer id) {
		Message entity = messageDao.getByID(id, entityManager);
		return entity;
	}

	@Override
	public List<Message> selectAll() {
		List<Message> messageList = messageDao.selectAll(entityManager);
		return messageList;
	}

	@Override
	public Message getFullInfo(Integer id) {
		return messageDao.getFullInfo(id, entityManager);
	}

	@Override
	public void updateOperation(Message entity) {
		messageDao.update(entity, entityManager);
	}

	@Override
	public void insertOperation(Message entity) {
		messageDao.insert(entity, entityManager);
	}

	@Override
	public void deleteOperation(Integer id) {
		messageDao.deleteByID(id, entityManager);
	}

	@Override
	public Integer getPK(Message entity) {
		return entity.getId();
	}
}
