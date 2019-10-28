package by.senla.study.board.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ranking")
public class Ranking extends BaseEntity {

	@Column(name = "feedback")
	@DecimalMin(value = "1")
	@DecimalMax(value = "5")
	@NotNull
	private Integer feedback;
	
	@Column(name = "text")
	private String text;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_from_id")
	private UserAccount userFrom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_whom_id")
	@NotNull
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
