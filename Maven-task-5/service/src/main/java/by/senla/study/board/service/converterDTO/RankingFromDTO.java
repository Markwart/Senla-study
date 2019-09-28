package by.senla.study.board.service.converterDTO;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IFromDTOConverter;
import by.senla.study.board.model.dto.RankingDTO;
import by.senla.study.board.model.entity.Ranking;
import by.senla.study.board.model.entity.UserAccount;

@Component
public class RankingFromDTO implements IFromDTOConverter<Ranking, RankingDTO> {

	@Override
	public Ranking apply(RankingDTO dto) {
		Ranking entity = new Ranking();
		entity.setId(dto.getId());
		entity.setFeedback(dto.getFeedback());
		
		UserAccount userFrom = new UserAccount();
		userFrom.setId(dto.getUserFromID());
		entity.setUserFrom(userFrom);
		
		UserAccount userWhom = new UserAccount();
		userWhom.setId(dto.getUserWhomID());
		entity.setUserWhom(userWhom);
		
		return entity;
	}

}
