package by.senla.study.service.converterDTO;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.senla.study.model.dto.ChatDTO;
import by.senla.study.model.entity.Chat;
import by.senla.study.model.entity.Message;

@Component
public class ChatFromDTO implements Function<ChatDTO, Chat> {

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
