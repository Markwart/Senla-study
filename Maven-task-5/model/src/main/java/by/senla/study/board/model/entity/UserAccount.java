package by.senla.study.board.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
	private Set<Ad> ads = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userFrom")
	private Set<Comment> comments = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Message> messages = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userFrom")
	private Set<Ranking> rankingFrom = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userWhom")
	private Set<Ranking> rankingWhom = new HashSet<>();

	@JoinTable(name = "user_2_ad", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "ad_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Ad> wishlist = new HashSet<>();

	@JoinTable(name = "user_2_chat", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "chat_id"))
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Chat> chats = new HashSet<>();

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

	public Set<Ad> getWishlist() {
		return wishlist;
	}

	public void setWishlist(Set<Ad> wishlist) {
		this.wishlist = wishlist;
	}

	public Set<Ad> getAds() {
		return ads;
	}

	public void setAds(Set<Ad> ads) {
		this.ads = ads;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<Ranking> getRankingFrom() {
		return rankingFrom;
	}

	public void setRankingFrom(Set<Ranking> rankingFrom) {
		this.rankingFrom = rankingFrom;
	}

	public Set<Ranking> getRankingWhom() {
		return rankingWhom;
	}

	public void setRankingWhom(Set<Ranking> rankingWhom) {
		this.rankingWhom = rankingWhom;
	}

	public Set<Chat> getChats() {
		return chats;
	}

	public void setChats(Set<Chat> chats) {
		this.chats = chats;
	}
}
