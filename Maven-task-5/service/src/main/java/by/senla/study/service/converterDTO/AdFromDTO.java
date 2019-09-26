package by.senla.study.service.converterDTO;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.senla.study.model.dto.AdDTO;
import by.senla.study.model.entity.Ad;
import by.senla.study.model.entity.Category;
import by.senla.study.model.entity.Comment;
import by.senla.study.model.entity.UserAccount;

@Component
public class AdFromDTO implements Function<AdDTO, Ad> {

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
