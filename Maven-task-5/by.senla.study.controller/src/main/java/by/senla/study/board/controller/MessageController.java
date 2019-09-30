package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IMessageService;
import by.senla.study.board.model.dto.MessageDto;
import by.senla.study.board.model.entity.Message;
import by.senla.study.board.service.mapper.MessageMapper;

@RestController
@RequestMapping(value = "/message")
public class MessageController extends AbstractController<Message, Integer, MessageDto> {

	private final IMessageService messageService;
	private final MessageMapper mapper;

	@Autowired
	public MessageController(IMessageService messageService, MessageMapper mapper) {
		super(Message.class, messageService, mapper);
		this.messageService = messageService;
		this.mapper = mapper;
	}
}
