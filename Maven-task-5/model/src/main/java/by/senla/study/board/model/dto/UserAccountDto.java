package by.senla.study.board.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


public class UserAccountDto extends BaseDto {

	@NotNull
	private String name;
	
	@Email
	@NotNull
	private String email;

	private PersonalDataDto personalData;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PersonalDataDto getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalDataDto personalData) {
		this.personalData = personalData;
	}

}
