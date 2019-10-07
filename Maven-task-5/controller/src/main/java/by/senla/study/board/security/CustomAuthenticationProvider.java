package by.senla.study.board.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.model.entity.PersonalData;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		final String login = authentication.getName();
		final String password = authentication.getCredentials().toString();

		PersonalData account = userAccountService.getUserByLogin(login);
		if (!login.equals(account.getLogin()) & !password.equals(account.getPassword())) {
			throw new BadCredentialsException("Bad Credentials");
		}
		
		final int userId = account.getId();
		
		List<String> userRoles = new ArrayList<>();
		userRoles.add("ROLE_" + account.getRole());

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String roleName : userRoles) {
			authorities.add(new SimpleGrantedAuthority(roleName));
		}
		return new ExtendedUsernamePasswordAuthenticationToken(userId, login, password, authorities);
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
