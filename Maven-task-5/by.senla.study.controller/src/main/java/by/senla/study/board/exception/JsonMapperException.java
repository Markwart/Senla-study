package by.senla.study.board.exception;

@SuppressWarnings("serial")
public class JsonMapperException extends RuntimeException {

	public JsonMapperException(String message) {
		super(message);
	}

	public JsonMapperException(String message, Throwable cause) {
		super(message, cause);
	}
}