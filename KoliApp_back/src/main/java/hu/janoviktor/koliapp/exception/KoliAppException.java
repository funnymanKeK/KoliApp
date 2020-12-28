package hu.janoviktor.koliapp.exception;

public class KoliAppException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public KoliAppException(String exMessage, Exception exception) {
		super(exMessage, exception);
	}

	public KoliAppException(String exMessage) {
		super(exMessage);
	}
}
