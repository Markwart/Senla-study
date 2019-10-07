package by.senla.study.board.exception;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import by.senla.study.board.service.exception.ServiceException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger(ExceptionHandlerController.class);
	private static final String EXCEPTION = "Exception Handler";
	private static final String INVALID_DATA = "Invalid Data";
	private static final String UNEXPECTED_ERROR = "Unexpected Error";
	private static final String NOT_FOUND = "Record is not Found";
	private static final String METHOD_NOT_ALLOWED = "Request Method not Supported "; // 405
	private static final String NOT_CONVERTED = "The parameter '%s' of value '%s' could not be converted to type '%s'";
	private static final String NO_HANDLER = "Incorrect Request";
	private static final String AUTHENTIFICATION = "Authentication Exception";

	@ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class, NullPointerException.class,
			ServiceException.class })
	public ResponseEntity<Object> handleInvalidDataError(Exception ex, WebRequest request) {
		LOGGER.log(Level.WARN, EXCEPTION, "Request: " + request.getContextPath() + " raised " + ex);
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, INVALID_DATA, ex.getMessage());
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		LOGGER.log(Level.WARN, EXCEPTION, ex);
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, UNEXPECTED_ERROR, ex.getMessage());
		return new ResponseEntity<Object>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
		LOGGER.log(Level.WARN, EXCEPTION, "Request: " + request.getContextPath() + " raised " + ex);
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, NOT_FOUND, ex.getMessage());
		return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		LOGGER.log(Level.WARN, EXCEPTION, "Request: " + request.getContextPath() + " raised " + ex);
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
				String.format(NOT_CONVERTED, ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()),
				ex.getMessage());
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.log(Level.WARN, EXCEPTION, "Request: " + request.getContextPath() + " raised " + ex);
		ApiError apiError = new ApiError(status, METHOD_NOT_ALLOWED, ex.getMessage());
		return new ResponseEntity<Object>(apiError, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		LOGGER.log(Level.WARN, EXCEPTION, "Request: " + request.getContextPath() + " raised " + ex);
		ApiError apiError = new ApiError(status, NO_HANDLER, ex.getMessage());
		return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ AuthenticationException.class, BadCredentialsException.class })
	public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		LOGGER.log(Level.WARN, EXCEPTION, "Request: " + request.getContextPath() + " raised " + ex);
		ApiError apiError = new ApiError(status, AUTHENTIFICATION, ex.getMessage());
		return new ResponseEntity<Object>(apiError, HttpStatus.UNAUTHORIZED);
	}
}
