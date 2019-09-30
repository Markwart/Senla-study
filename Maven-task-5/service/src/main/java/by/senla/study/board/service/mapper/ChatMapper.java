package by.senla.study.board.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.senla.study.board.model.dto.ChatDto;
import by.senla.study.board.model.entity.Chat;

@Component
public class ChatMapper extends AbstractMapper<Chat, ChatDto> {

	@Autowired
	ChatMapper() {
		super(Chat.class, ChatDto.class);
	}

}
