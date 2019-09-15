package by.senla.study.service.impl;

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
	public Integer getPK(Message entity) {
		return entity.getId();
	}
}
