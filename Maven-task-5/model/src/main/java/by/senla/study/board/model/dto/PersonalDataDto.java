package by.senla.study.board.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import by.senla.study.board.model.enums.Roles;

public class PersonalDataDto extends BaseDto {

	private Roles role;

	@Size(min = 6)
	@NotNull
	private String password;

	@NotNull
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
