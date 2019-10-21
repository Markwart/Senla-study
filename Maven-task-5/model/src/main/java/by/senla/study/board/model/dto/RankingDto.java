package by.senla.study.board.model.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

public class RankingDto extends BaseDto {

	@DecimalMin(value = "1")
	@DecimalMax(value = "5")
	private Integer feedback;
	
	private String text;

	private Integer userFromId;

	private Integer userWhomId;

	public Integer getFeedback() {
		return feedback;
	}

	public void setFeedback(Integer feedback) {
		this.feedback = feedback;
	}

	public Integer getUserFromId() {
		return userFromId;
	}

	public void setUserFromId(Integer userFromId) {
		this.userFromId = userFromId;
	}

	public Integer getUserWhomId() {
		return userWhomId;
	}

	public void setUserWhomId(Integer userWhomId) {
		this.userWhomId = userWhomId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
