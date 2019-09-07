package by.senla.study.api.service;

import java.util.List;

import by.senla.study.model.entity.UserAccount;

public interface IUserAccountService {

	UserAccount createEntity();

	UserAccount get(Integer id);
	
	void update(UserAccount entity);

	void insert(UserAccount entity);

	void delete(Integer id);
	
	List<UserAccount> selectAll();
}
