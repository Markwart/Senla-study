package by.senla.study.board.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class RankingDto extends BaseDto {

	@Min(value = 1)
	@Max(value = 5)
	private Integer feedback;

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
}
