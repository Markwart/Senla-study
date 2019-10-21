package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.dto.LoginDto;
import by.senla.study.board.security.CustomAuthenticationProvider;

@RestController
public class LoginController {

	private static final String UNAUTHORIZED = "Error. User is not authorized";
	private static final String AUTHORIZED = "User is authorized";
	
	@Autowired
    private CustomAuthenticationProvider authProvider;
	
	@PostMapping
	public void login(@RequestBody LoginDto dto) {
		
	/*	AuthenticationManagerBuilder.

		final String login = authentication.getName();
		final String password = authentication.getCredentials().toString();*/
	}
	
	
}
