package by.senla.study.service.impl;

import java.util.List;

import by.senla.study.api.dao.IChatDao;
import by.senla.study.api.service.IChatService;
import by.senla.study.dao.impl.ChatDao;
import by.senla.study.model.entity.Chat;

public class ChatService extends AbstractService<Chat, Integer> implements IChatService {

	private IChatDao chatDao = ChatDao.getInstance();
	private static ChatService instance;

	private ChatService() {
		super(Chat.class);
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
	public Chat getByID(Integer id) {
		Chat entity = chatDao.getByID(id, entityManager);
		return entity;
	}

	@Override
	public List<Chat> selectAll() {
		List<Chat> chatList = chatDao.selectAll(entityManager);
		return chatList;
	}

	@Override
	public Chat getFullInfo(Integer id) {
		return chatDao.getFullInfo(id, entityManager);
	}

	@Override
	public void updateOperation(Chat entity) {
		chatDao.update(entity, entityManager);		
	}

	@Override
	public void insertOperation(Chat entity) {
		chatDao.insert(entity, entityManager);		
	}

	@Override
	public void deleteOperation(Integer id) {
		chatDao.deleteByID(id, entityManager);		
	}

	@Override
	public Integer getPK(Chat entity) {
		return entity.getId();
	}
}
