package by.senla.study.board.service.converterDTO;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IFromDTOConverter;
import by.senla.study.board.model.dto.MessageDTO;
import by.senla.study.board.model.entity.Chat;
import by.senla.study.board.model.entity.Message;
import by.senla.study.board.model.entity.UserAccount;

@Component
	public class MessageFromDTO implements IFromDTOConverter<Message, MessageDTO> {

	@Override
	public Message apply(MessageDTO dto) {
		Message entity = new Message();
		entity.setId(dto.getId());
		entity.setText(dto.getText());
		
		Chat chat = new Chat();
		chat.setId(dto.getChatID());
		entity.setChat(chat);
		
		UserAccount userAccount = new UserAccount();
		userAccount.setId(dto.getUserID());
		entity.setUser(userAccount);
		
		return entity;
	}

}
