package by.senla.study.service.impl;

import java.util.Date;

import by.senla.study.api.dao.IChatDao;
import by.senla.study.api.service.IChatService;
import by.senla.study.dao.impl.ChatDao;
import by.senla.study.model.entity.Chat;

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
	public Chat updateOperation(Chat entity) {
		entity.setUpdated(new Date());
		return entity;
	}

	@Override
	public Chat insertOperation(Chat entity) {
		entity.setCreated(new Date());
		return entity;
	}

	@Override
	public Integer getPK(Chat entity) {
		return entity.getId();
	}

	@Override
	public Chat mergeOperation(Chat entity) {
		entity.setUpdated(new Date());
		return entity;
	}
}
