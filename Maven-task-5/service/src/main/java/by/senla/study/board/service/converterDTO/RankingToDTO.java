package by.senla.study.board.service.converterDTO;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IToDTOConverter;
import by.senla.study.board.model.dto.RankingDTO;
import by.senla.study.board.model.entity.Ranking;

@Component
public class RankingToDTO implements IToDTOConverter<Ranking, RankingDTO> {

	@Override
	public RankingDTO apply(Ranking entity) {
		RankingDTO dto = new RankingDTO();
		dto.setId(entity.getId());
		dto.setFeedback(entity.getFeedback());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		
		dto.setUserFromID(entity.getUserFrom().getId());
		dto.setUserWhomID(entity.getUserWhom().getId());
		
		return dto;
	}

}
