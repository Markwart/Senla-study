package by.senla.study.board.service.converterDTO;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IToDTOConverter;
import by.senla.study.board.model.dto.MessageDTO;
import by.senla.study.board.model.entity.Message;

@Component
public class MessageToDTO implements IToDTOConverter<Message, MessageDTO> {

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
