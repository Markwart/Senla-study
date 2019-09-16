package by.senla.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IChatDao;
import by.senla.study.api.service.IChatService;
import by.senla.study.model.entity.Chat;

@Service
public class ChatService extends AbstractService<Chat, Integer> implements IChatService {

	private final IChatDao chatDao;

	@Autowired
	public ChatService(IChatDao chatDao) {
		super(Chat.class, chatDao);
		this.chatDao = chatDao;
	}
}
