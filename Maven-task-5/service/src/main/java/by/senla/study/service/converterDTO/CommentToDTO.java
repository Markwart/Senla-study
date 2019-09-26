package by.senla.study.service.converterDTO;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.senla.study.model.dto.CommentDTO;
import by.senla.study.model.entity.Comment;

@Component
public class CommentToDTO implements Function<Comment, CommentDTO> {

	@Override
	public CommentDTO apply(Comment entity) {
		CommentDTO dto = new CommentDTO();
		dto.setId(entity.getId());
		dto.setText(entity.getText());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		
		dto.setAdID(entity.getAd().getId());
		dto.setUserFromID(entity.getUserFrom().getId());
		
		return dto;
	}

}
