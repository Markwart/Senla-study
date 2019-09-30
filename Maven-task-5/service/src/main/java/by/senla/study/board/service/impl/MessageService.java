package by.senla.study.board.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.IMessageDao;
import by.senla.study.board.api.service.IMessageService;
import by.senla.study.board.model.entity.Message;

@Service
@Transactional
public class MessageService extends AbstractService<Message, Integer> implements IMessageService {
	
	private static final Logger LOGGER = LogManager.getLogger(MessageService.class);

	private final IMessageDao messageDao;

	@Autowired
	public MessageService(IMessageDao messageDao) {
		super(Message.class, messageDao);
		this.messageDao = messageDao;
	}
}
