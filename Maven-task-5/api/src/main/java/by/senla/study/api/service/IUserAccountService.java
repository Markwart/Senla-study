package by.senla.study.api.service;

import java.io.IOException;

import by.senla.study.model.entity.UserAccount;

public interface IUserAccountService extends GenericService<UserAccount, Integer> {

	UserAccount getFullInfo(Integer id);

	void exportToCSV();
}
