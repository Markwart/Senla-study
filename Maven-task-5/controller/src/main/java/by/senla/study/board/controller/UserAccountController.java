package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.model.dto.PersonalDataDto;
import by.senla.study.board.model.dto.UserAccountDto;
import by.senla.study.board.model.entity.PersonalData;
import by.senla.study.board.model.entity.UserAccount;
import by.senla.study.board.service.mapper.PersonalDataMapper;
import by.senla.study.board.service.mapper.UserAccountMapper;

@RestController
@RequestMapping(value = "/userAccount")
public class UserAccountController extends AbstractController<UserAccount, Integer, UserAccountDto> {

	private final IUserAccountService userAccountService;
	private final UserAccountMapper userAccountMapper;
	private final PersonalDataMapper personalDataMapper;

	@Autowired
	public UserAccountController(IUserAccountService userAccountService, UserAccountMapper userAccountMapper,
			PersonalDataMapper personalDataMapper) {
		super(UserAccount.class, userAccountService, userAccountMapper);
		this.userAccountService = userAccountService;
		this.userAccountMapper = userAccountMapper;
		this.personalDataMapper = personalDataMapper;
	}

	@PostMapping(value = "/createNewUser")
	public String createNewUser(UserAccountDto userAccountDto, PersonalDataDto personalDataDto) {
		UserAccount userAccount = userAccountMapper.toEntity(userAccountDto);
		PersonalData personalData = personalDataMapper.toEntity(personalDataDto);
		userAccountService.createNewUser(userAccount, personalData);
		return String.format(CREATED, getEntityClass().getSimpleName(), userAccount.getId());
	}

	@PutMapping(value = "/{userId}/editUser")
	public String editUser(@PathVariable(name = "userId", required = true) Integer userId,
			UserAccountDto userAccountDto, PersonalDataDto personalDataDto) {
		UserAccount userAccount = userAccountService.getById(userId);
		userAccountService.setFieldsAndUpdate(userAccount, userAccountDto, personalDataDto);
		return String.format(UPDATED, getEntityClass().getSimpleName(), userId);
	}
}
