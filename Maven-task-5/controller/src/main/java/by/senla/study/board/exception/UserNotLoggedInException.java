package by.senla.study.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNotLoggedInException extends Exception {
	
	public UserNotLoggedInException(String message) {
		super(message);
	}
}