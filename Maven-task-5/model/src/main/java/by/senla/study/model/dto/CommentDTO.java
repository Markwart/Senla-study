package by.senla.study.model.dto;

public class CommentDTO extends BaseDTO {

	private String text;

	private Integer adID;

	private Integer userFromID;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getAdID() {
		return adID;
	}

	public void setAdID(Integer adID) {
		this.adID = adID;
	}

	public Integer getUserFromID() {
		return userFromID;
	}

	public void setUserFromID(Integer userFromID) {
		this.userFromID = userFromID;
	}
}
