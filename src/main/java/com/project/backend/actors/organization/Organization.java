package com.project.backend.actors.organization;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.project.backend.login.models.User;

@Entity
public class Organization extends User {

	@NotBlank
	@Size(min = 3, max = 20)
	private String NIU;

	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}
//
//	public Organization(String username, String email, String password, Set<Role> roles, String adress, Long phone,
//			boolean verified, LocalDate createOn) {
//		super(username, email, password, roles, adress, phone, verified, createOn);
//		// TODO Auto-generated constructor stub
//	}
//
//	public Organization(String username, String email, String password) {
//		super(username, email, password);
//		// TODO Auto-generated constructor stub
//	}

	public Organization(String username, String email, String password, String adress, Long phone, boolean verified,
			String NIu) {
		super(username, email, password, adress, phone, verified);
		this.NIU = NIu;
	}

	public Organization(String username, String email, String password, String NIu) {
		super(username, email, password);
		this.NIU = NIu;
	}

	public String getNIU() {
		return NIU;
	}

	public void setNIU(String nIU) {
		NIU = nIU;
	}
}
