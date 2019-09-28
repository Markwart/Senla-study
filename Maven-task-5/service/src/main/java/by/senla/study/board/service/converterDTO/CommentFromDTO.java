package by.senla.study.board.service.converterDTO;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IFromDTOConverter;
import by.senla.study.board.model.dto.CommentDTO;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.entity.Comment;
import by.senla.study.board.model.entity.UserAccount;

@Component
public class CommentFromDTO implements IFromDTOConverter<Comment, CommentDTO> {

	@Override
	public Comment apply(CommentDTO dto) {
		Comment entity = new Comment();
		entity.setId(dto.getId());
		entity.setText(dto.getText());

		Ad ad = new Ad();
		ad.setId(dto.getAdID());
		entity.setAd(ad);
		
		UserAccount userAccount = new UserAccount();
		userAccount.setId(dto.getUserFromID());
		entity.setUserFrom(userAccount);

		return entity;
	}

}
