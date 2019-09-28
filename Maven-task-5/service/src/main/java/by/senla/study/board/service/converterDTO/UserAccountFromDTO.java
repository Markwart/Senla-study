package by.senla.study.board.service.converterDTO;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IFromDTOConverter;
import by.senla.study.board.model.dto.UserAccountDTO;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.entity.Chat;
import by.senla.study.board.model.entity.Comment;
import by.senla.study.board.model.entity.Message;
import by.senla.study.board.model.entity.PersonalData;
import by.senla.study.board.model.entity.Ranking;
import by.senla.study.board.model.entity.UserAccount;

@Component
public class UserAccountFromDTO implements IFromDTOConverter<UserAccount, UserAccountDTO> {

	@Override
	public UserAccount apply(UserAccountDTO dto) {
		UserAccount entity = new UserAccount();
		entity.setId(dto.getId());
		entity.setEmail(dto.getEmail());
		entity.setName(dto.getName());

		PersonalData personalData = new PersonalData();
		personalData.setId(dto.getPersonalDataID());
		entity.setPersonalData(personalData);

		Set<Integer> ads = dto.getAdsID();
		if (!ads.isEmpty()) {
			entity.setAds(ads.stream().map((id) -> {
				Ad ad = new Ad();
				ad.setId(id);
				return ad;
			}).collect(Collectors.toSet()));
		}

		Set<Integer> comments = dto.getCommentsID();
		if (!comments.isEmpty()) {
			entity.setComments(comments.stream().map((id) -> {
				Comment comment = new Comment();
				comment.setId(id);
				return comment;
			}).collect(Collectors.toSet()));
		}

		Set<Integer> messages = dto.getMessagesID();
		if (!messages.isEmpty()) {
			entity.setMessages(messages.stream().map((id) -> {
				Message message = new Message();
				message.setId(id);
				return message;
			}).collect(Collectors.toSet()));
		}

		Set<Integer> rankingFrom = dto.getRankingFromID();
		if (!rankingFrom.isEmpty()) {
			entity.setRankingFrom(rankingFrom.stream().map((id) -> {
				Ranking rank = new Ranking();
				rank.setId(id);
				return rank;
			}).collect(Collectors.toSet()));
		}

		Set<Integer> rankingWhom = dto.getRankingWhomID();
		if (!rankingWhom.isEmpty()) {
			entity.setRankingWhom(rankingWhom.stream().map((id) -> {
				Ranking rank = new Ranking();
				rank.setId(id);
				return rank;
			}).collect(Collectors.toSet()));
		}

		Set<Integer> wishList = dto.getWishlistAdsID();
		if (!wishList.isEmpty()) {
			entity.setWishlist(wishList.stream().map((id) -> {
				Ad ad = new Ad();
				ad.setId(id);
				return ad;
			}).collect(Collectors.toSet()));
		}

		Set<Integer> chats = dto.getChatsID();
		if (!chats.isEmpty()) {
			entity.setChats(chats.stream().map((id) -> {
				Chat chat = new Chat();
				chat.setId(id);
				return chat;
			}).collect(Collectors.toSet()));
		}

		return entity;
	}
}
