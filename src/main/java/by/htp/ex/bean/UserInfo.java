package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String birthday;


	public UserInfo(String login, String password, String name, String surname, String email, String birthday) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birthday = birthday;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserInfo that = (UserInfo) o;
		return Objects.equals(login, that.login)
				&& Objects.equals(password, that.password)
				&& Objects.equals(name, that.name)
				&& Objects.equals(email, that.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, password, name, email);
	}
}
