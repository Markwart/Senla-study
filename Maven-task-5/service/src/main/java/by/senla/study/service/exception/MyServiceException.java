package by.senla.study.service.exception;

@SuppressWarnings("serial")
public class MyServiceException extends Exception {

	public MyServiceException(Exception e) {
		throw new RuntimeException(e);
	}
}
