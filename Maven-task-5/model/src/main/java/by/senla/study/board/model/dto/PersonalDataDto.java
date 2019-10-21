package by.senla.study.board.model.dto;

import javax.validation.constraints.Size;

import by.senla.study.board.model.enums.Roles;

public class PersonalDataDto extends BaseDto {

	private Roles role;

	@Size(min = 6)
	private String password;

	private String login;

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
