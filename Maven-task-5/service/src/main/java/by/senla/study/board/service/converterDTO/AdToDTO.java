package by.senla.study.board.service.converterDTO;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IToDTOConverter;
import by.senla.study.board.model.dto.AdDTO;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.entity.Comment;

@Component
public class AdToDTO implements IToDTOConverter<Ad, AdDTO>{

	@Override
	public AdDTO apply(Ad entity) {
		AdDTO dto = new AdDTO();
		dto.setId(entity.getId());
		dto.setImage(entity.getImage());
		dto.setPrice(entity.getPrice());
		dto.setText(entity.getText());
		dto.setTheme(entity.getTheme());
		dto.setStatus(entity.getStatus());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		dto.setCategoryID(entity.getCategory().getId());
		dto.setSellerID(entity.getSeller().getId());

		Set<Comment> comments = entity.getComments();
		if (!comments.isEmpty()) {
			dto.setCommentsID(comments.stream().map(Comment::getId).collect(Collectors.toSet()));
		}

		return dto;
	}
}
