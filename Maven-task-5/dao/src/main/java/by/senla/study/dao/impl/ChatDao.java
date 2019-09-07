package by.senla.study.dao.impl;

import by.senla.study.api.dao.IChatDao;
import by.senla.study.model.entity.Chat;

public class ChatDao extends AbstractDao<Chat, Integer> implements IChatDao {
	
	private static ChatDao instance;

	private ChatDao() {
	}

	public static ChatDao getInstance() {
		if (instance == null) {
			instance = new ChatDao();
		}
		return instance;
	}
}
