package com.project.backend.actors.admin;

import javax.persistence.Entity;

import com.project.backend.login.models.User;

@Entity
public class Admin extends User{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String email, String password) {
		super(username, email, password);
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String email, String password, String adress, Long phone,
			boolean verified) {
		super(username, email, password, adress, phone, verified);
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String email, String password, String adress, Long phone, boolean verified,
			String avatar) {
		super(username, email, password, adress, phone, verified, avatar);
		// TODO Auto-generated constructor stub
	}

}
