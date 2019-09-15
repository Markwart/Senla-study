package by.senla.study.service.impl;

import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IChatDao;
import by.senla.study.api.service.IChatService;
import by.senla.study.dao.impl.ChatDao;
import by.senla.study.model.entity.Chat;

@Service
public class ChatService extends AbstractService<Chat, Integer> implements IChatService {

	private static IChatDao chatDao = ChatDao.getInstance();
	private static ChatService instance;

	private ChatService() {
		super(Chat.class, chatDao);
	}

	public static ChatService getInstance() {
		if (instance == null) {
			instance = new ChatService();
		}
		return instance;
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
