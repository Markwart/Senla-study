package by.senla.study.board.service.converterDTO;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IFromDTOConverter;
import by.senla.study.board.model.dto.AdDTO;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.entity.Category;
import by.senla.study.board.model.entity.Comment;
import by.senla.study.board.model.entity.UserAccount;

@Component
public class AdFromDTO implements IFromDTOConverter<Ad, AdDTO> {

	@Override
	public Ad apply(AdDTO dto) {
		Ad entity = new Ad();
		entity.setId(dto.getId());
		entity.setImage(dto.getImage());
		entity.setPrice(dto.getPrice());
		entity.setText(dto.getText());
		entity.setTheme(dto.getText());
		entity.setStatus(dto.getStatus());

		Category category = new Category();
		category.setId(dto.getCategoryID());
		entity.setCategory(category);

		UserAccount seller = new UserAccount();
		seller.setId(dto.getSellerID());
		entity.setSeller(seller);

		Set<Integer> comments = dto.getCommentsID();
		if (!comments.isEmpty()) {
			entity.setComments(comments.stream().map((id) -> {
				Comment comment = new Comment();
				comment.setId(id);
				return comment;
			}).collect(Collectors.toSet()));
		}

		return entity;
	}
}
