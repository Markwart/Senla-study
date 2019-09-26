package by.senla.study.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class RankingDTO extends BaseDTO {

	@Min(value = 1)
	@Max(value = 5)
	private Integer feedback;

	private Integer userFromID;

	private Integer userWhomID;

	public Integer getFeedback() {
		return feedback;
	}

	public void setFeedback(Integer feedback) {
		this.feedback = feedback;
	}

	public Integer getUserFromID() {
		return userFromID;
	}

	public void setUserFromID(Integer userFromID) {
		this.userFromID = userFromID;
	}

	public Integer getUserWhomID() {
		return userWhomID;
	}

	public void setUserWhomID(Integer userWhomID) {
		this.userWhomID = userWhomID;
	}

}
