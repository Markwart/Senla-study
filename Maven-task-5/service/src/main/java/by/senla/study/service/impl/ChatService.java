package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IChatDao;
import by.senla.study.api.service.IChatService;
import by.senla.study.dao.impl.ChatDao;
import by.senla.study.model.entity.Chat;

public class ChatService implements IChatService {

	private static final Logger LOGGER = LogManager.getLogger(ChatService.class);
	private IChatDao dao = ChatDao.getInstance();
	private static ChatService instance;

	private ChatService() {
	}

	public static ChatService getInstance() {
		if (instance == null) {
			instance = new ChatService();
		}
		return instance;
	}

	public Chat createEntity() {
		return new Chat();
	}

	public Chat get(Integer id) {
		Chat entity = dao.get(id);
		return entity;
	}

	public void update(Chat entity) {
		dao.update(entity);
	}

	public void insert(Chat entity) {
		dao.insert(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public List<Chat> selectAll() {
		List<Chat> all = dao.selectAll();
		return all;
	}

}
