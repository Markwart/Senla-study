package by.senla.study.service.converterDTO;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.senla.study.model.dto.ChatDTO;
import by.senla.study.model.entity.Chat;
import by.senla.study.model.entity.Message;

@Component
public class ChatToDTO implements Function<Chat, ChatDTO> {

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
