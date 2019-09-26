package by.senla.study.model.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserAccountDTO extends BaseDTO {

	@NotNull
	private String name;
	
	@Email
	@NotNull
	private String email;

	private Integer personalDataID;

	private Set<Integer> adsID;

	private Set<Integer> commentsID;

	private Set<Integer> messagesID;

	private Set<Integer> rankingFromID;

	private Set<Integer> rankingWhomID;

	private Set<Integer> wishlistAdsID;

	private Set<Integer> chatsID;

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

	public Integer getPersonalDataID() {
		return personalDataID;
	}

	public void setPersonalDataID(Integer personalDataID) {
		this.personalDataID = personalDataID;
	}

	public Set<Integer> getAdsID() {
		return adsID;
	}

	public void setAdsID(Set<Integer> adsID) {
		this.adsID = adsID;
	}

	public Set<Integer> getCommentsID() {
		return commentsID;
	}

	public void setCommentsID(Set<Integer> commentsID) {
		this.commentsID = commentsID;
	}

	public Set<Integer> getMessagesID() {
		return messagesID;
	}

	public void setMessagesID(Set<Integer> messagesID) {
		this.messagesID = messagesID;
	}

	public Set<Integer> getRankingFromID() {
		return rankingFromID;
	}

	public void setRankingFromID(Set<Integer> rankingFromID) {
		this.rankingFromID = rankingFromID;
	}

	public Set<Integer> getRankingWhomID() {
		return rankingWhomID;
	}

	public void setRankingWhomID(Set<Integer> rankingWhomID) {
		this.rankingWhomID = rankingWhomID;
	}

	public Set<Integer> getWishlistAdsID() {
		return wishlistAdsID;
	}

	public void setWishlistAdsID(Set<Integer> wishlistAdsID) {
		this.wishlistAdsID = wishlistAdsID;
	}

	public Set<Integer> getChatsID() {
		return chatsID;
	}

	public void setChatsID(Set<Integer> chatsID) {
		this.chatsID = chatsID;
	}
}
