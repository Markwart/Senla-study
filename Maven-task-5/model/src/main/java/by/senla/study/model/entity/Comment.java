package by.senla.study.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

	@Column(name = "text")
	private String text;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	@Column(name = "ad_id")
	private Ad ad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	@Column(name = "user_from_id")
	private UserAccount userFrom;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public UserAccount getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(UserAccount userFrom) {
		this.userFrom = userFrom;
	}

}
