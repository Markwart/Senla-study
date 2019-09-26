package by.senla.study.model.dto;

public class MessageDTO extends BaseDTO {

	private String text;

	private Integer userID;

	private Integer chatID;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getChatID() {
		return chatID;
	}

	public void setChatID(Integer chatID) {
		this.chatID = chatID;
	}
}
