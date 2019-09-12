package by.senla.study.service.impl;

import java.util.Date;

import by.senla.study.api.dao.IMessageDao;
import by.senla.study.api.service.IMessageService;
import by.senla.study.dao.impl.MessageDao;
import by.senla.study.model.entity.Message;

public class MessageService extends AbstractService<Message, Integer> implements IMessageService {

	private static IMessageDao messageDao = MessageDao.getInstance();
	private static MessageService instance;

	private MessageService() {
		super(Message.class, messageDao);
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
	public Message updateOperation(Message entity) {
		entity.setUpdated(new Date());
		return entity;
	}

	@Override
	public Message insertOperation(Message entity) {
		entity.setCreated(new Date());
		return entity;
	}

	@Override
	public Integer getPK(Message entity) {
		return entity.getId();
	}

	@Override
	public Message mergeOperation(Message entity) {
		entity.setUpdated(new Date());
		return entity;
	}
}
