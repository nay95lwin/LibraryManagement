package com.librarymanagement.person.admin;

import com.librarymanagement.person.Person;
import com.librarymanagement.utility.Utility;

public class Admin extends Person {
	private String password;

	public Admin(int id, String name, String password) {
		super(id, name);
		this.password = password;
	}

	public String getPassword() {
		return Utility.decryptString(password);
	}

	public void setPassword(String password) {
		this.password = Utility.encryptString(password);
	}

	@Override
	public String toString() {
		return "Admin [AdminId=" + id + ", AdminName=" + name + ", Password=" + password + "]";
	}

	public String toCsvString() {
		return id + "," + name + "," + password;
	}
}
