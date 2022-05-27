package com.project.backend.collector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.project.backend.company.Company;
import com.project.backend.login.models.User;

@Entity
public class Collector extends User {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Company company;
	
	public Collector() {
		super();
	}

	public Collector(String username, String email, String password, String adress, Long phone,
			boolean verified) {
		super(username, email, password, adress, phone, verified);
	}

	public Collector(String username, String email, String password) {
		super(username, email, password);
	}

//	public Company getCom() {
//		return company;
//	}

	public void setCom(Company com) {
		this.company = com;
	}
}
