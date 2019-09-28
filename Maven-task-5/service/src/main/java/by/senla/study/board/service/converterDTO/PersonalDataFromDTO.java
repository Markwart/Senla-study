package by.senla.study.board.service.converterDTO;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IFromDTOConverter;
import by.senla.study.board.model.dto.PersonalDataDTO;
import by.senla.study.board.model.entity.PersonalData;
import by.senla.study.board.model.entity.UserAccount;

@Component
public class PersonalDataFromDTO implements IFromDTOConverter<PersonalData, PersonalDataDTO> {

	@Override
	public PersonalData apply(PersonalDataDTO dto) {
		PersonalData entity = new PersonalData();
		entity.setId(dto.getId());
		entity.setLogin(dto.getLogin());
		entity.setPassword(dto.getPassword());
		entity.setRole(dto.getRole());
		
		UserAccount userAccount = new UserAccount();
		userAccount.setId(dto.getUserAccountID());
		entity.setUserAccount(userAccount);
		
		return entity;
	}

}
