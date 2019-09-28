package by.senla.study.board.service.converterDTO;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IFromDTOConverter;
import by.senla.study.board.model.dto.ChatDTO;
import by.senla.study.board.model.entity.Chat;
import by.senla.study.board.model.entity.Message;

@Component
public class ChatFromDTO implements IFromDTOConverter<Chat, ChatDTO> {

	@Override
	public Chat apply(ChatDTO dto) {
		Chat entity = new Chat();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		
		Set<Integer> messages = dto.getMessagesID();
		if (!messages.isEmpty()) {
			entity.setMessages(messages.stream().map((id) -> {
				Message message = new Message();
				message.setId(id);
				return message;
			}).collect(Collectors.toSet()));
		}
		return entity;
	}

}
