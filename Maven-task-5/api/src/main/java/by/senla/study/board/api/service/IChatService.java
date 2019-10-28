package by.senla.study.board.api.service;

import by.senla.study.board.model.dto.ChatDto;
import by.senla.study.board.model.entity.Chat;

public interface IChatService extends GenericService<Chat, Integer, ChatDto>{

	void createNewChat(Integer sellerId, Integer userId);
	
}
