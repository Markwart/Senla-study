package by.senla.study.model.entity;

import java.util.HashSet;
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

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

@CsvEntity(fileName = "UserAccount.csv")
@Entity
@Table(name = "user_account")
public class UserAccount extends BaseEntity {

	@CsvProperty(columnNumber = 1, propertyType = PropertyType.SIMPLE)
	@Column(name = "name")
	private String name;

	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SIMPLE)
	@Column(name = "email")
	private String email;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount")
	private PersonalData personalData;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "seller")
	private Set<Ad> ads = new HashSet<Ad>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userFrom")
	private Set<Comment> comments = new HashSet<Comment>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Message> messages = new HashSet<Message>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userFrom")
	private Set<Ranking> rankingFrom = new HashSet<Ranking>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userWhom")
	private Set<Ranking> rankingWhom = new HashSet<Ranking>();

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

}
