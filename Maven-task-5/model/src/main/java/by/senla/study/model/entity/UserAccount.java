package by.senla.study.model.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_account")
public class UserAccount extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount")
	private PersonalData personalData;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "seller")
	private List<Ad> ads = new ArrayList<Ad>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userFrom")
	private List<Comment> comments = new ArrayList<Comment>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Message> messages = new ArrayList<Message>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userFrom")
	private List<Ranking> rankingFrom = new ArrayList<Ranking>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userWhom")
	private List<Ranking> rankingWhom = new ArrayList<Ranking>();

	@JoinTable(name = "user_2_ad", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "ad_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Ad> wishlist = new HashSet<Ad>();

	@JoinTable(name = "user_2_chat", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "chat_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Ad> chats = new HashSet<Ad>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Ranking> getRankingFrom() {
		return rankingFrom;
	}

	public void setRankingFrom(List<Ranking> rankingFrom) {
		this.rankingFrom = rankingFrom;
	}

	public List<Ranking> getRankingWhom() {
		return rankingWhom;
	}

	public void setRankingWhom(List<Ranking> rankingWhom) {
		this.rankingWhom = rankingWhom;
	}

	public Set<Ad> getWishlist() {
		return wishlist;
	}

	public void setWishlist(Set<Ad> wishlist) {
		this.wishlist = wishlist;
	}

	public Set<Ad> getChats() {
		return chats;
	}

	public void setChats(Set<Ad> chats) {
		this.chats = chats;
	}

}
