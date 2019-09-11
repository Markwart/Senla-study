package by.senla.study.api.service;

import by.senla.study.model.entity.UserAccount;

public interface IUserAccountService extends GenericService<UserAccount, Integer> {

	UserAccount getFullInfo(Integer id);
}
