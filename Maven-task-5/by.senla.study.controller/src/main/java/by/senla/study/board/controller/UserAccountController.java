package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.model.dto.UserAccountDto;
import by.senla.study.board.model.entity.UserAccount;
import by.senla.study.board.service.mapper.UserAccountMapper;

@RestController
@RequestMapping(value = "/userAccount")
public class UserAccountController extends AbstractController<UserAccount, Integer, UserAccountDto> {

	private final IUserAccountService userAccountService;
	private final UserAccountMapper mapper;

	@Autowired
	public UserAccountController(IUserAccountService userAccountService, UserAccountMapper mapper) {
		super(UserAccount.class, userAccountService, mapper);
		this.userAccountService = userAccountService;
		this.mapper = mapper;
	}
}
