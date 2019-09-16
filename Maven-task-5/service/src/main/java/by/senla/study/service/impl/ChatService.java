package by.senla.study.service.impl;

import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IChatDao;
import by.senla.study.api.service.IChatService;
import by.senla.study.model.entity.Chat;

@Service
public class ChatService extends AbstractService<Chat, Integer> implements IChatService {

	private static IChatDao chatDao;

	private ChatService() {
		super(Chat.class, chatDao);
	}

	@Override
	public Chat createEntity() {
		return new Chat();
	}

	@Override
	public Integer getPK(Chat entity) {
		return entity.getId();
	}
}
