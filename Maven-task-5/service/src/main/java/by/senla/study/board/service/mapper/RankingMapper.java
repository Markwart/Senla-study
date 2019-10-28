package by.senla.study.board.service.mapper;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.senla.study.board.api.dao.IUserAccountDao;
import by.senla.study.board.model.dto.RankingDto;
import by.senla.study.board.model.entity.Ranking;

@Component
public class RankingMapper extends AbstractMapper<Ranking, RankingDto> {

	private final ModelMapper mapper;
	private final IUserAccountDao userAccountDao;

	@Autowired
	RankingMapper(ModelMapper mapper, IUserAccountDao userAccountDao) {
		super(Ranking.class, RankingDto.class);
		this.mapper = mapper;
		this.userAccountDao = userAccountDao;
	}

	@PostConstruct
	public void setupMapper() {
		mapper.createTypeMap(Ranking.class, RankingDto.class).addMappings(m -> m.skip(RankingDto::setUserFromId))
				.setPostConverter(toDtoConverter());
		mapper.getTypeMap(Ranking.class, RankingDto.class).addMappings(m -> m.skip(RankingDto::setUserWhomId))
				.setPostConverter(toDtoConverter());

		mapper.createTypeMap(RankingDto.class, Ranking.class).addMappings(m -> m.skip(Ranking::setUserFrom))
				.setPostConverter(toEntityConverter());
		mapper.getTypeMap(RankingDto.class, Ranking.class).addMappings(m -> m.skip(Ranking::setUserWhom))
				.setPostConverter(toEntityConverter());
	}

	@Override
	public void mapSpecificFields(Ranking source, RankingDto destination) {
		destination.setUserFromId(source.getUserFrom().getId());
		destination.setUserWhomId(source.getUserWhom().getId());
	}

	@Override
	public void mapSpecificFields(RankingDto source, Ranking destination) {
		destination.setUserFrom(userAccountDao.getById(source.getUserFromId()));
		destination.setUserWhom(userAccountDao.getById(source.getUserWhomId()));
	}
}
