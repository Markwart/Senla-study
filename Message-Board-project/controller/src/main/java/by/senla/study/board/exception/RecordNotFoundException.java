package by.senla.study.board.exception;

@SuppressWarnings("serial")
public class RecordNotFoundException extends RuntimeException {

	public RecordNotFoundException(String exception) {
		super(exception);
	}
	
	public RecordNotFoundException() {
	}
}