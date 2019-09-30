package by.senla.study.board.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.IChatDao;
import by.senla.study.board.api.service.IChatService;
import by.senla.study.board.model.entity.Chat;

@Service
@Transactional
public class ChatService extends AbstractService<Chat, Integer> implements IChatService {
	
	private static final Logger LOGGER = LogManager.getLogger(ChatService.class);

	private final IChatDao chatDao;

	@Autowired
	public ChatService(IChatDao chatDao) {
		super(Chat.class, chatDao);
		this.chatDao = chatDao;
	}
}
