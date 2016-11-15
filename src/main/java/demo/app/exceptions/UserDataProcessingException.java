package demo.app.exceptions;

import org.springframework.validation.Errors;

/**
 * Runtime exception when received user data is invalid
 * 
 * @author rushikesh
 *
 */
public class UserDataProcessingException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final Errors errors;

	public UserDataProcessingException(Errors errors) {
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}
}
