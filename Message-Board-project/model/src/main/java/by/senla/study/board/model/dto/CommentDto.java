package by.senla.study.board.model.dto;

public class CommentDto extends BaseDto {

	private String text;

	private Integer adId;

	private Integer userFromId;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getAdId() {
		return adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public Integer getUserFromId() {
		return userFromId;
	}

	public void setUserFromId(Integer userFromId) {
		this.userFromId = userFromId;
	}
}
