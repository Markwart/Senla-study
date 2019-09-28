package by.senla.study.board.model.dto;

import java.util.Set;

public class ChatDTO extends BaseDTO {

	private String name;

	private Set<Integer> messagesID;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Integer> getMessagesID() {
		return messagesID;
	}

	public void setMessagesID(Set<Integer> messagesID) {
		this.messagesID = messagesID;
	}
}
