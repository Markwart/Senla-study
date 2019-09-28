package by.senla.study.board.service.exception;

@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}