package by.senla.study.board.model.dto;

import javax.validation.constraints.Email;

public class UserAccountDto extends BaseDto {

	private String name;
	
	@Email
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
