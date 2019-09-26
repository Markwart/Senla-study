package by.senla.study.service.converterDTO;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.senla.study.model.dto.MessageDTO;
import by.senla.study.model.entity.Message;

@Component
public class MessageToDTO implements Function<Message, MessageDTO> {

	@Override
	public MessageDTO apply(Message entity) {
		MessageDTO dto = new MessageDTO();
		dto.setId(entity.getId());
		dto.setText(entity.getText());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		
		dto.setChatID(entity.getChat().getId());
		dto.setUserID(entity.getUser().getId());
		
		return dto;
	}

}
