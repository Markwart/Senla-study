package by.senla.study.board.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

	private static final Logger LOGGER = LogManager.getLogger(ExceptionHandlerController.class);
	private static final String EXCEPTION = "Controller layer. Exception handler";
	private static final String RESPONSE = "Sorry, something gone wrong";

	@ExceptionHandler(Exception.class)
	public String handleError(HttpServletRequest req, Exception ex) {
		LOGGER.log(Level.WARN, EXCEPTION, "Request: " + req.getRequestURL() + " raised " + ex);
		return RESPONSE;
	}
}