package demo.app.dtos;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * DTO to represent incoming User resources
 * 
 * @author rushikesh
 *
 */
public class UserDto implements IResource {

	@NotEmpty(message = "Name cannot be empty")
	@Size(min = 3, max = 20, message = "Invalid name!")
	private String name;

	// TODO: maybe provide custom validator for email
	@NotEmpty(message = "Email cannot be empty!")
	private String email;

	public UserDto() {
	}

	public UserDto(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that instanceof UserDto) {
			return this.email.equals(((UserDto) that).email);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.email.hashCode();
	}

	@Override
	public String toString() {
		return "{name: " + name + ", email: " + email + "}";
	}
}
