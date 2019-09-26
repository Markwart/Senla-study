package by.senla.study.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.api.dao.IChatDao;
import by.senla.study.api.service.IChatService;
import by.senla.study.model.entity.Chat;

@Service
@Transactional
public class ChatService extends AbstractService<Chat, Integer> implements IChatService {
	
	private final Logger LOGGER = LogManager.getLogger(getEntityClass());

	private final IChatDao chatDao;

	@Autowired
	public ChatService(IChatDao chatDao) {
		super(Chat.class, chatDao);
		this.chatDao = chatDao;
	}
}
