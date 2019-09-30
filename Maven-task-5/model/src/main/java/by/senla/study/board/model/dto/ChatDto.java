package by.senla.study.board.model.dto;

import java.util.Set;

public class ChatDto extends BaseDto {

	private String name;

	private Set<MessageDto> messages;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<MessageDto> getMessages() {
		return messages;
	}

	public void setMessages(Set<MessageDto> messages) {
		this.messages = messages;
	}
}
