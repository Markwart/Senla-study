package by.senla.study.board.service.mapper;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.senla.study.board.api.dao.IUserAccountDao;
import by.senla.study.board.model.dto.PersonalDataDto;
import by.senla.study.board.model.entity.PersonalData;

@Component
public class PersonalDataMapper extends AbstractMapper<PersonalData, PersonalDataDto> {

	private final ModelMapper mapper;
	private final IUserAccountDao userAccountDao;

	@Autowired
	PersonalDataMapper(ModelMapper mapper, IUserAccountDao userAccountDao) {
		super(PersonalData.class, PersonalDataDto.class);
		this.mapper = mapper;
		this.userAccountDao = userAccountDao;
	}

	@PostConstruct
	public void setupMapper() {
		mapper.createTypeMap(PersonalData.class, PersonalDataDto.class)
				.addMappings(m -> m.skip(PersonalDataDto::setUserAccountId)).setPostConverter(toDtoConverter());

		mapper.createTypeMap(PersonalDataDto.class, PersonalData.class)
				.addMappings(m -> m.skip(PersonalData::setUserAccount)).setPostConverter(toEntityConverter());
	}

	@Override
	public void mapSpecificFields(PersonalData source, PersonalDataDto destination) {
		destination.setUserAccountId(source.getUserAccount().getId());
	}

	@Override
	public void mapSpecificFields(PersonalDataDto source, PersonalData destination) {
		destination.setUserAccount(userAccountDao.getByID(source.getUserAccountId()));
	}
}
