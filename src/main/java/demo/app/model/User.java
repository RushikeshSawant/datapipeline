package demo.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import demo.app.dtos.UserDto;

/**
 * Entity representing User resource
 * 
 * @author rushikesh
 *
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String name;

	@NotNull
	private String email;

	public User() {
	}

	public User(long id) {
		this.id = id;
	}

	public User(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long value) {
		this.id = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that instanceof UserDto) {
			return this.email.equals(((User) that).email);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.email.hashCode();
	}
}
