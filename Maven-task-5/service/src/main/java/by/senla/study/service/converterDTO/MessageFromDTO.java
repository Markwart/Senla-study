package by.senla.study.service.converterDTO;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.senla.study.model.dto.MessageDTO;
import by.senla.study.model.entity.Chat;
import by.senla.study.model.entity.Message;
import by.senla.study.model.entity.UserAccount;

@Component
public class MessageFromDTO implements Function<MessageDTO, Message> {

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
