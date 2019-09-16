package by.senla.study.service.impl;

import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IMessageDao;
import by.senla.study.api.service.IMessageService;
import by.senla.study.model.entity.Message;

@Service
public class MessageService extends AbstractService<Message, Integer> implements IMessageService {

	private static IMessageDao messageDao;

	private MessageService() {
		super(Message.class, messageDao);
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
