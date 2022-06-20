package com.project.backend.actors.company;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.project.backend.actors.collector.Collector;
import com.project.backend.actors.household.Household;
import com.project.backend.actors.organization.Organization;
import com.project.backend.login.models.User;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Company extends User {

	private String NIU;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Collector> collectors = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable( name = "household_following", joinColumns = @JoinColumn(referencedColumnName = "company_id"), inverseJoinColumns = @JoinColumn(referencedColumnName = "household_id"))
	private Set<Household> hhdFollowers = new HashSet<>();

	@JoinTable( name = "org_ollowing", joinColumns = @JoinColumn(referencedColumnName = "company_id"), inverseJoinColumns = @JoinColumn(referencedColumnName = "organization_id"))
	private Set<Organization> orgFollowers = new HashSet<>();
	
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company(String username, String email, String password, String adress, Long phone, boolean verified,
			String niu) {
		super(username, email, password, adress, phone, verified);
		NIU = niu;
	}

	public Company(String username, String email, String password, String niu) {
		super(username, email, password);
		NIU = niu;
	}

	public String getNIU() {
		return NIU;
	}

	public void setNIU(String nIU) {
		NIU = nIU;
	}

	public Set<Collector> getCollectors() {
		return collectors;
	}

	public void setCollectors(Set<Collector> collectors) {
		this.collectors = collectors;
	}

}
