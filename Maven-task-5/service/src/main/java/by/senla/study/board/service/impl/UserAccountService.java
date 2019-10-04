package by.senla.study.board.service.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.IAdDao;
import by.senla.study.board.api.dao.IPersonalDataDao;
import by.senla.study.board.api.dao.IUserAccountDao;
import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.model.dto.PersonalDataDto;
import by.senla.study.board.model.dto.UserAccountDto;
import by.senla.study.board.model.entity.PersonalData;
import by.senla.study.board.model.entity.UserAccount;
import by.senla.study.board.model.enums.Roles;

@Service
@Transactional
public class UserAccountService extends AbstractService<UserAccount, Integer, UserAccountDto>
		implements IUserAccountService {

	private static final Logger LOGGER = LogManager.getLogger(UserAccountService.class);

	private final IUserAccountDao userAccountDao;
	private final IAdDao adDao;
	private final IPersonalDataDao personalDataDao;

	@Autowired
	public UserAccountService(IUserAccountDao userAccountDao, IAdDao adDao, IPersonalDataDao personalDataDao) {
		super(UserAccount.class, userAccountDao);
		this.userAccountDao = userAccountDao;
		this.adDao = adDao;
		this.personalDataDao = personalDataDao;
	}

	@Override
	public void addToWishList(Integer adId, Integer userId) {
		UserAccount userAccount = userAccountDao.getById(userId);
		userAccount.getWishlist().add(adDao.getById(adId));
		update(userAccount);
	}

	@Override
	public void setFieldsAndUpdate(UserAccount entity, UserAccountDto dto) {
		if (dto.getEmail() != null)
			entity.setEmail(dto.getEmail());
		if (dto.getName() != null)
			entity.setName(dto.getName());
		update(entity);
	}

	@Override
	public void setFieldsAndUpdate(UserAccount userAccount, UserAccountDto userAccountDto,
			PersonalDataDto personalDataDto) {
		if (userAccountDto.getEmail() != null)
			userAccount.setEmail(userAccountDto.getEmail());
		if (userAccountDto.getName() != null)
			userAccount.setName(userAccountDto.getName());

		PersonalData personalData = userAccount.getPersonalData();

		if (personalDataDto.getLogin() != null)
			personalData.setLogin(personalDataDto.getLogin());
		if (personalDataDto.getPassword() != null)
			personalData.setPassword(personalDataDto.getPassword());
		if (personalDataDto.getRole() != null)
			personalData.setRole(personalDataDto.getRole());

		update(userAccount);
		personalDataDao.update(personalData);
	}

	@Override
	public void createNewUser(UserAccount userAccount, PersonalData personalData) {
		Date current = new Date();

		insert(userAccount);
		personalData.setId(userAccount.getId());
		personalData.setUserAccount(userAccount);
		personalData.setRole(Roles.USER);
		personalData.setCreated(current);
		personalData.setUpdated(current);
		personalDataDao.insert(personalData);
	}
}
