package by.senla.study.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.dto.ResponseDto;
import by.senla.study.board.model.dto.PersonalDataDto;
import by.senla.study.board.model.dto.UserAccountDto;
import by.senla.study.board.model.entity.PersonalData;
import by.senla.study.board.model.entity.UserAccount;
import by.senla.study.board.service.mapper.UserAccountMapper;

@RestController
@RequestMapping(value = "/userAccount")
public class UserAccountController extends AbstractController<UserAccount, Integer, UserAccountDto> {

	private final IUserAccountService userAccountService;
	private final UserAccountMapper userAccountMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserAccountController(IUserAccountService userAccountService, UserAccountMapper userAccountMapper) {
		super(UserAccount.class, userAccountService, userAccountMapper);
		this.userAccountService = userAccountService;
		this.userAccountMapper = userAccountMapper;
	}

	@PostMapping(value = "/create-new")
	public ResponseDto createNewUser(@Valid @RequestBody UserAccountDto userAccountDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseDto(INVALID);
		} else {
			UserAccount userAccount = userAccountMapper.toEntity(userAccountDto);
			userAccount.getPersonalData()
					.setPassword(passwordEncoder.encode(userAccount.getPersonalData().getPassword()));
			userAccountService.createNewUser(userAccount);
			return new ResponseDto(String.format(CREATED, getEntityClass().getSimpleName(), userAccount.getId()));
		}
	}

	@PutMapping(value = "/{userId}/edit-user")
	public ResponseDto editUser(@PathVariable(name = "userId", required = true) Integer userId,
			@Valid @RequestBody UserAccountDto userAccountDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseDto(INVALID);
		} else {
			UserAccount userAccount = userAccountService.getById(userId);
			userAccountService.setFieldsAndUpdate(userAccount, userAccountDto);
			return new ResponseDto(String.format(UPDATED, getEntityClass().getSimpleName(), userId));
		}
	}

	@PutMapping(value = "/{userId}/edit-creds")
	public ResponseDto editPersonalData(@PathVariable(name = "userId", required = true) Integer userId,
			@Valid @RequestBody PersonalDataDto personalDataDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseDto(INVALID);
		} else {
			if (personalDataDto != null) {
				personalDataDto.setPassword(passwordEncoder.encode(personalDataDto.getPassword()));
			}
			PersonalData personalData = userAccountService.getFullInfo(userId).getPersonalData();
			userAccountService.setFieldsAndUpdate(personalData, personalDataDto);
			return new ResponseDto(String.format(UPDATED, personalData.getClass().getSimpleName(), userId));
		}
	}
}
