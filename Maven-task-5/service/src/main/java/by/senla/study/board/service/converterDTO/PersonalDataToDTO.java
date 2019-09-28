package by.senla.study.board.service.converterDTO;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IToDTOConverter;
import by.senla.study.board.model.dto.PersonalDataDTO;
import by.senla.study.board.model.entity.PersonalData;

@Component
public class PersonalDataToDTO implements IToDTOConverter<PersonalData, PersonalDataDTO>{

	@Override
	public PersonalDataDTO apply(PersonalData entity) {
		PersonalDataDTO dto = new PersonalDataDTO();
		dto.setId(entity.getId());
		dto.setLogin(entity.getLogin());
		dto.setPassword(entity.getPassword());
		dto.setRole(entity.getRole());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		
		dto.setUserAccountID(entity.getUserAccount().getId());
		
		return dto;
	}

}
