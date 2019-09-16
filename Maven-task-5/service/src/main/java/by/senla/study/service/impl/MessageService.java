package by.senla.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IMessageDao;
import by.senla.study.api.service.IMessageService;
import by.senla.study.model.entity.Message;

@Service
public class MessageService extends AbstractService<Message, Integer> implements IMessageService {

	private final IMessageDao messageDao;

	@Autowired
	public MessageService(IMessageDao messageDao) {
		super(Message.class, messageDao);
		this.messageDao = messageDao;
	}
}
