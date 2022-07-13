package com.project.backend.actors.household;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicUpdate;

import com.project.backend.login.models.User;

@Entity
@DynamicUpdate
public class Household extends User {

	private String familyName;

	public Household() {
		super();
		// TODO Auto-generated constructor stub
	}
//
//	public Household(String username, String email, String password) {
//		super(username, email, password);
//		// TODO Auto-generated constructor stub
//	}

	public Household(String username, String email, String password, String familyName) {
		super(username, email, password);
		this.familyName = familyName;
	}
	
	public Household(String username, String email, String password, String adress, Long phone, boolean verified,
			String familyName, String avatar) {
		super(username, email, password, adress, phone, verified, avatar);
		this.familyName = familyName;
	}
	
	public Household(String username, String email, String password, String adress, Long phone, boolean verified,
			String familyName) {
		super(username, email, password, adress, phone, verified);
		this.familyName = familyName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

}
