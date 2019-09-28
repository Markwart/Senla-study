package by.senla.study.board.service.converterDTO;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IToDTOConverter;
import by.senla.study.board.model.dto.CommentDTO;
import by.senla.study.board.model.entity.Comment;

@Component
public class CommentToDTO implements IToDTOConverter<Comment, CommentDTO> {

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
