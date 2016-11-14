package demo.app.exceptions;

import demo.app.dtos.UserDto;

/**
 * Runtime exception when received user data is invalid
 * 
 * @author rushikesh
 *
 */
public class UserDataProcessingException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final UserDto user;

	public UserDataProcessingException(UserDto userDto) {
		this.user = userDto;
	}

	public String getMessage() {
		return "Invalid data: { name: " + user.getName() + ", email: " + user.getEmail() + " }";
	}
}
