package by.senla.study.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import by.senla.study.board.service.utils.ServiceContext;

@Configuration
@EnableWebSecurity
@Import(ServiceContext.class)
@ComponentScan(basePackages = { "by.senla.study.board.security" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
		.accessDeniedHandler(accessDeniedHandler())
		.and().authorizeRequests()
		.antMatchers("/ad/filter").permitAll()
		.antMatchers("/ad/search").permitAll()
		.antMatchers("/ad/sellect-all").permitAll()
		.antMatchers("/userAccount/create-new").anonymous()
		.antMatchers("/ad/{\\d+}/delete").hasAnyRole("ADMIN", "USER")
		.antMatchers("/category/**").hasRole("ADMIN")
		.antMatchers("/{\\w+}/{\\d+}/delete").hasRole("ADMIN")
		.antMatchers("/{\\w+}/sellect-all").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().httpBasic();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}
}
