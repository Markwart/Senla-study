package by.senla.study.board.service.converterDTO;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IToDTOConverter;
import by.senla.study.board.model.dto.UserAccountDTO;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.entity.Chat;
import by.senla.study.board.model.entity.Comment;
import by.senla.study.board.model.entity.Message;
import by.senla.study.board.model.entity.Ranking;
import by.senla.study.board.model.entity.UserAccount;

@Component
public class UserAccountToDTO implements IToDTOConverter<UserAccount, UserAccountDTO> {

	@Override
	public UserAccountDTO apply(UserAccount entity) {
		UserAccountDTO dto = new UserAccountDTO();
		dto.setId(entity.getId());
		dto.setEmail(entity.getEmail());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		dto.setPersonalDataID(entity.getPersonalData().getId());

		Set<Ad> ads = entity.getAds();
		if (!ads.isEmpty()) {
			dto.setAdsID(ads.stream().map(Ad::getId).collect(Collectors.toSet()));
		}

		Set<Comment> comments = entity.getComments();
		if (!comments.isEmpty()) {
			dto.setCommentsID(comments.stream().map(Comment::getId).collect(Collectors.toSet()));
		}

		Set<Message> messages = entity.getMessages();
		if (!messages.isEmpty()) {
			dto.setMessagesID(messages.stream().map(Message::getId).collect(Collectors.toSet()));
		}

		Set<Ranking> rankingFrom = entity.getRankingFrom();
		if (!rankingFrom.isEmpty()) {
			dto.setRankingFromID(rankingFrom.stream().map(Ranking::getId).collect(Collectors.toSet()));
		}

		Set<Ranking> rankingWhom = entity.getRankingWhom();
		if (!rankingWhom.isEmpty()) {
			dto.setRankingWhomID(rankingWhom.stream().map(Ranking::getId).collect(Collectors.toSet()));
		}

		Set<Ad> wishList = entity.getWishlist();
		if (!wishList.isEmpty()) {
			dto.setWishlistAdsID(wishList.stream().map(Ad::getId).collect(Collectors.toSet()));
		}

		Set<Chat> chats = entity.getChats();
		if (!chats.isEmpty()) {
			dto.setChatsID(chats.stream().map(Chat::getId).collect(Collectors.toSet()));
		}

		return dto;
	}
}
