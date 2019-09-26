package by.senla.study.service.converterDTO;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.senla.study.model.dto.RankingDTO;
import by.senla.study.model.entity.Ranking;
import by.senla.study.model.entity.UserAccount;

@Component
public class RankingFromDTO implements Function<RankingDTO, Ranking> {

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
