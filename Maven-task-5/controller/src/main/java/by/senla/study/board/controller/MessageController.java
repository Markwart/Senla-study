package by.senla.study.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IChatService;
import by.senla.study.board.api.service.IMessageService;
import by.senla.study.board.model.dto.MessageDto;
import by.senla.study.board.model.entity.Message;
import by.senla.study.board.service.mapper.MessageMapper;

@RestController
@RequestMapping(value = "/message")
public class MessageController extends AbstractController<Message, Integer, MessageDto> {

	private final IMessageService messageService;
	private final MessageMapper mapper;
	private final IChatService chatService;

	@Autowired
	public MessageController(IMessageService messageService, MessageMapper mapper, IChatService chatService) {
		super(Message.class, messageService, mapper);
		this.messageService = messageService;
		this.mapper = mapper;
		this.chatService = chatService;
	}

	@PostMapping(value = "/{chatId}/add")
	public String addMessage(@PathVariable(name = "chatId", required = true) Integer chatId, Integer userId,
			MessageDto dto) {
		dto.setChatId(chatId);
		dto.setUserId(userId);
		Message entity = mapper.toEntity(dto);
		messageService.insert(entity);
		return String.format(CREATED, getEntityClass().getSimpleName(), entity.getId());
	}

	@GetMapping(value = "/{chatId}/messages")
	public List<MessageDto> getMessagesOfChat(@PathVariable(name = "chatId", required = true) Integer chatId) {
		Set<Message> entities = chatService.getFullInfo(chatId).getMessages();
		List<MessageDto> dtos = new ArrayList<>();
		for (Message entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		return dtos;
	}
}
