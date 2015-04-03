package exceptions;

@SuppressWarnings("serial")
public class DuplicateArgumentException extends Exception {

	public DuplicateArgumentException(String msg) {
		super(msg);
	}
}
