package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IChatService;
import by.senla.study.board.model.dto.ChatDTO;
import by.senla.study.board.model.entity.Chat;
import by.senla.study.board.service.converterDTO.ChatFromDTO;
import by.senla.study.board.service.converterDTO.ChatToDTO;

@RestController
@RequestMapping(value = "/chat")
public class ChatController extends AbstractController<Chat, Integer, ChatDTO> {

	private final IChatService chatService;
	private final ChatToDTO toDTOConverter;
	private final ChatFromDTO fromDTOConverter;

	@Autowired
	public ChatController(IChatService chatService, ChatToDTO toDTOConverter, ChatFromDTO fromDTOConverter) {
		super(Chat.class, chatService, toDTOConverter, fromDTOConverter);
		this.chatService = chatService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
	}

}
