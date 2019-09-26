package by.senla.study.service.converterDTO;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.senla.study.model.dto.CommentDTO;
import by.senla.study.model.entity.Ad;
import by.senla.study.model.entity.Comment;
import by.senla.study.model.entity.UserAccount;

@Component
public class CommentFromDTO implements Function<CommentDTO, Comment> {

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
