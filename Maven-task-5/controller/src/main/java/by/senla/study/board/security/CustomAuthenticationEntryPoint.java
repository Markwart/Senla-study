package by.senla.study.board.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public final class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final String UNAUTHORIZED = "{ \"status\": \" %s \", \"error\": \" %s \"}";

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException authException) throws IOException {

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().println(String.format(UNAUTHORIZED, response.getStatus(), authException.getMessage()));
	}
}