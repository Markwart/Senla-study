package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IMessageService;
import by.senla.study.board.model.dto.MessageDTO;
import by.senla.study.board.model.entity.Message;
import by.senla.study.board.service.converterDTO.MessageFromDTO;
import by.senla.study.board.service.converterDTO.MessageToDTO;

@RestController
@RequestMapping(value = "/message")
public class MessageController extends AbstractController<Message, Integer, MessageDTO> {

	private final IMessageService messageService;
	private final MessageToDTO toDTOConverter;
	private final MessageFromDTO fromDTOConverter;

	@Autowired
	public MessageController(IMessageService messageService, MessageToDTO toDTOConverter,
			MessageFromDTO fromDTOConverter) {
		super(Message.class, messageService, toDTOConverter, fromDTOConverter);
		this.messageService = messageService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
	}
}
