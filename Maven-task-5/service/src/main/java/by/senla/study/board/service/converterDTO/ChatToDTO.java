package by.senla.study.board.service.converterDTO;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IToDTOConverter;
import by.senla.study.board.model.dto.ChatDTO;
import by.senla.study.board.model.entity.Chat;
import by.senla.study.board.model.entity.Message;

@Component
public class ChatToDTO implements IToDTOConverter<Chat, ChatDTO> {

	@Override
	public ChatDTO apply(Chat entity) {
		ChatDTO dto = new ChatDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		
		Set<Message> messages = entity.getMessages();
		if (!messages.isEmpty()) {
			dto.setMessagesID(messages.stream().map(Message::getId).collect(Collectors.toSet()));
		}
		return dto;
	}
}
