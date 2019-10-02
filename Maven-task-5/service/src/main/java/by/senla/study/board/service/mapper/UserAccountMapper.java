package by.senla.study.board.service.mapper;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.senla.study.board.api.dao.IPersonalDataDao;
import by.senla.study.board.model.dto.UserAccountDto;
import by.senla.study.board.model.entity.UserAccount;

@Component
public class UserAccountMapper extends AbstractMapper<UserAccount, UserAccountDto> {

	private final ModelMapper mapper;
	private final IPersonalDataDao personalDataDao;

	@Autowired
	UserAccountMapper(ModelMapper mapper, IPersonalDataDao personalDataDao) {
		super(UserAccount.class, UserAccountDto.class);
		this.mapper = mapper;
		this.personalDataDao = personalDataDao;
	}

	@PostConstruct
	public void setupMapper() {
		mapper.createTypeMap(UserAccount.class, UserAccountDto.class)
				.addMappings(m -> m.skip(UserAccountDto::setPersonalDataId)).setPostConverter(toDtoConverter());

		mapper.createTypeMap(UserAccountDto.class, UserAccount.class)
				.addMappings(m -> m.skip(UserAccount::setPersonalData)).setPostConverter(toEntityConverter());
	}

	@Override
	public void mapSpecificFields(UserAccount source, UserAccountDto destination) {
		destination.setPersonalDataId(source.getPersonalData().getId());
	}

	@Override
	public void mapSpecificFields(UserAccountDto source, UserAccount destination) {
		destination.setPersonalData(personalDataDao.getById(source.getPersonalDataId()));
	}
}
