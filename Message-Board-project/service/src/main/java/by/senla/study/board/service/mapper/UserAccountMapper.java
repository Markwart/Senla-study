package by.senla.study.board.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.senla.study.board.model.dto.UserAccountDto;
import by.senla.study.board.model.entity.UserAccount;

@Component
public class UserAccountMapper extends AbstractMapper<UserAccount, UserAccountDto> {

	@Autowired
	UserAccountMapper() {
		super(UserAccount.class, UserAccountDto.class);
	}
}
