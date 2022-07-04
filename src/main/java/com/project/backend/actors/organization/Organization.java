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
	
	private String showCasePath;
	
	private String logoPath;

	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Organization(String username, String email, String password, String adress, Long phone, boolean verified,
			String NIu, String logo , String showCase, String avatar) {
		super(username, email, password, adress, phone, verified, avatar);
		this.NIU = NIu;
		this.logoPath = logo;
		this.showCasePath = showCase;
	}
	
	public Organization(String username, String email, String password, String adress, Long phone, boolean verified,
			String NIu, String logo , String showCase) {
		super(username, email, password, adress, phone, verified);
		this.NIU = NIu;
		this.logoPath = logo;
		this.showCasePath = showCase;
	}

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

	public String getShowCasePath() {
		return showCasePath;
	}

	public void setShowCasePath(String showCasePath) {
		this.showCasePath = showCasePath;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	
}
