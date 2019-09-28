package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.model.dto.UserAccountDTO;
import by.senla.study.board.model.entity.UserAccount;
import by.senla.study.board.service.converterDTO.UserAccountFromDTO;
import by.senla.study.board.service.converterDTO.UserAccountToDTO;

@RestController
@RequestMapping(value = "/userAccount")
public class UserAccountController extends AbstractController<UserAccount, Integer, UserAccountDTO> {

	private final IUserAccountService userAccountService;
	private final UserAccountToDTO toDTOConverter;
	private final UserAccountFromDTO fromDTOConverter;

	@Autowired
	public UserAccountController(IUserAccountService userAccountService, UserAccountToDTO toDTOConverter,
			UserAccountFromDTO fromDTOConverter) {
		super(UserAccount.class, userAccountService, toDTOConverter, fromDTOConverter);
		this.userAccountService = userAccountService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
	}
}
