package by.senla.study.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "ranking")
public class Ranking extends BaseEntity {

	@Column(name = "feedback")
	@Min(value = 1)
	@Max(value = 5)
	private Integer feedback;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_from_id")
	private UserAccount userFrom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_whom_id")
	private UserAccount userWhom;

	public Integer getFeedback() {
		return feedback;
	}

	public void setFeedback(Integer feedback) {
		this.feedback = feedback;
	}

	public UserAccount getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(UserAccount userFrom) {
		this.userFrom = userFrom;
	}

	public UserAccount getUserWhom() {
		return userWhom;
	}

	public void setUserWhom(UserAccount userWhom) {
		this.userWhom = userWhom;
	}
}
