package by.senla.study.board.api.service;

import by.senla.study.board.model.dto.PersonalDataDto;
import by.senla.study.board.model.dto.UserAccountDto;
import by.senla.study.board.model.entity.PersonalData;
import by.senla.study.board.model.entity.UserAccount;

public interface IUserAccountService extends GenericService<UserAccount, Integer, UserAccountDto> {

	void addToWishList(Integer adId, Integer userId);

	void createNewUser(UserAccount userAccount, PersonalData personalData);

	void setFieldsAndUpdate(UserAccount userAccount, UserAccountDto userAccountDto, PersonalDataDto personalDataDto);

	PersonalData getUserByLogin(String login);

}
