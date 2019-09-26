package by.senla.study.service.converterDTO;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.senla.study.model.dto.RankingDTO;
import by.senla.study.model.entity.Ranking;

@Component
public class RankingToDTO implements Function<Ranking, RankingDTO> {

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
