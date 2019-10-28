package by.senla.study.board.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import by.senla.study.board.model.enums.Roles;

@Entity
@Table(name = "personal_data")
public class PersonalData {

	@Id
	@Column(name = "id", updatable = false)
	private Integer id;

	@Column(name = "created", updatable = false)
	private Date created;

	@Column(name = "updated")
	private Date updated;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	@NotNull
	private Roles role;

	@Column(name = "password")
	@NotNull
	private String password;

	@Column(name = "login")
	@NotNull
	private String login;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserAccount userAccount;

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

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
