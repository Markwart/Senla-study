package by.senla.study.service.converterDTO;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.senla.study.model.dto.PersonalDataDTO;
import by.senla.study.model.entity.PersonalData;
import by.senla.study.model.entity.UserAccount;

@Component
public class PersonalDataFromDTO implements Function<PersonalDataDTO, PersonalData> {

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
