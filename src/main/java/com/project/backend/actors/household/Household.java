package com.project.backend.actors.household;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.project.backend.actors.company.Company;
import com.project.backend.login.models.User;

@Entity
@DynamicUpdate
public class Household extends User {

	private String familyName;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "hhdAbonnees")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Company> companies = new HashSet<>();

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

	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

}
