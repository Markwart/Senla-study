package by.senla.study.board.model.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserAccountDto extends BaseDto {

	@NotNull
	private String name;
	
	@Email
	@NotNull
	private String email;

	private Integer personalDataId;

	private Set<AdDto> ads;

	private Set<CommentDto> comments;

	private Set<MessageDto> messages;

	private Set<RankingDto> rankingFrom;

	private Set<RankingDto> rankingWhom;

	private Set<AdDto> wishlist;

	private Set<ChatDto> chats;

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

	public Integer getPersonalDataId() {
		return personalDataId;
	}

	public void setPersonalDataId(Integer personalDataId) {
		this.personalDataId = personalDataId;
	}

	public Set<AdDto> getAds() {
		return ads;
	}

	public void setAds(Set<AdDto> ads) {
		this.ads = ads;
	}

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}

	public Set<MessageDto> getMessages() {
		return messages;
	}

	public void setMessages(Set<MessageDto> messages) {
		this.messages = messages;
	}

	public Set<RankingDto> getRankingFrom() {
		return rankingFrom;
	}

	public void setRankingFrom(Set<RankingDto> rankingFrom) {
		this.rankingFrom = rankingFrom;
	}

	public Set<RankingDto> getRankingWhom() {
		return rankingWhom;
	}

	public void setRankingWhom(Set<RankingDto> rankingWhom) {
		this.rankingWhom = rankingWhom;
	}

	public Set<AdDto> getWishlist() {
		return wishlist;
	}

	public void setWishlist(Set<AdDto> wishlist) {
		this.wishlist = wishlist;
	}

	public Set<ChatDto> getChats() {
		return chats;
	}

	public void setChats(Set<ChatDto> chats) {
		this.chats = chats;
	}
}
