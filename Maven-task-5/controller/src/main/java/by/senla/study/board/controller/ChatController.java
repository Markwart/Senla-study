package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IChatService;
import by.senla.study.board.model.dto.ChatDto;
import by.senla.study.board.model.entity.Chat;
import by.senla.study.board.service.mapper.ChatMapper;

@RestController
@RequestMapping(value = "/chat")
public class ChatController extends AbstractController<Chat, Integer, ChatDto> {

	private final IChatService chatService;
	private final ChatMapper mapper;

	@Autowired
	public ChatController(IChatService chatService, ChatMapper mapper) {
		super(Chat.class, chatService, mapper);
		this.chatService = chatService;
		this.mapper = mapper;
	}

}
