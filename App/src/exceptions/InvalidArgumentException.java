package exceptions;

@SuppressWarnings("serial")
public class InvalidArgumentException extends Exception {

	public InvalidArgumentException(String msg) {
		super(msg);
	}
}
