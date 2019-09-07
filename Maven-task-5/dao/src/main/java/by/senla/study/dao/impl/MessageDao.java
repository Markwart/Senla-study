package by.senla.study.dao.impl;

import by.senla.study.api.dao.IMessageDao;
import by.senla.study.model.entity.Message;

public class MessageDao extends AbstractDao<Message, Integer> implements IMessageDao {

	private static MessageDao instance;

	private MessageDao() {
	}

	public static MessageDao getInstance() {
		if (instance == null) {
			instance = new MessageDao();
		}
		return instance;
	}
}
