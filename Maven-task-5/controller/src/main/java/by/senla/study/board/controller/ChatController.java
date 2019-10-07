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
import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.dto.ResponseDto;
import by.senla.study.board.exception.RecordNotFoundException;
import by.senla.study.board.model.dto.ChatDto;
import by.senla.study.board.model.entity.Chat;
import by.senla.study.board.service.mapper.ChatMapper;

@RestController
@RequestMapping(value = "/chat")
public class ChatController extends AbstractController<Chat, Integer, ChatDto> {

	private static final String CREATED_CHAT = "new chat with user`s id=%d and id=%d was created";

	private final IChatService chatService;
	private final ChatMapper mapper;
	private final IUserAccountService userAccountService;

	@Autowired
	public ChatController(IChatService chatService, ChatMapper mapper, IUserAccountService userAccountService) {
		super(Chat.class, chatService, mapper);
		this.chatService = chatService;
		this.mapper = mapper;
		this.userAccountService = userAccountService;
	}

	@GetMapping(value = "/myChats")
	public List<ChatDto> myChats() {
		Set<Chat> entities = userAccountService.getFullInfo(getLoggedUserId()).getChats();
		List<ChatDto> dtos = new ArrayList<>();
		for (Chat entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		if (dtos.isEmpty()) {
			throw new RecordNotFoundException();
		}
		return dtos;
	}

	@PostMapping(value = "/{sellerId}/createNew")
	public ResponseDto createNewChat(@PathVariable(name = "sellerId", required = true) Integer sellerId) {
		chatService.createNewChat(sellerId, getLoggedUserId());
		return new ResponseDto(String.format(CREATED_CHAT, sellerId, getLoggedUserId()));
	}
}
