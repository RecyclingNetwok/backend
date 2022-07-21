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

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.project.backend.actors.collector.Collector;
import com.project.backend.actors.household.Household;
import com.project.backend.actors.organization.Organization;
import com.project.backend.login.models.User;
import com.project.backend.services.post.Post;

import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate
public class Company extends User {

	private String NIU;

	private String showCasePath;

	private String logoPath;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Collector> collectors = new HashSet<>();

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Post> posts = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "hhd_abonnements", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "househod_id"))
	private Set<Household> hhdAbonnees = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "org_abonnements", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "org_id"))
	private Set<Organization> orgAbonnees = new HashSet<>();

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company(String username, String email, String password, String adress, Long phone, boolean verified,
			String niu, String logo, String showCase, String avatar) {
		super(username, email, password, adress, phone, verified, avatar);
		NIU = niu;
		this.logoPath = logo;
		this.showCasePath = showCase;
	}

	public Company(String username, String email, String password, String adress, Long phone, boolean verified,
			String niu, String logo, String showCase) {
		super(username, email, password, adress, phone, verified);
		NIU = niu;
		this.logoPath = logo;
		this.showCasePath = showCase;
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

	public void setNIU(String nIU) {
		NIU = nIU;
	}

	public Set<Collector> getCollectors() {
		return collectors;
	}

	public void setCollectors(Set<Collector> collectors) {
		this.collectors = collectors;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Household> getHhdAbonnees() {
		return hhdAbonnees;
	}

	public void setHhdAbonnees(Set<Household> hhdAbonnees) {
		this.hhdAbonnees = hhdAbonnees;
	}

	public Set<Organization> getOrgAbonnees() {
		return orgAbonnees;
	}

	public void setOrgAbonnees(Set<Organization> orgAbonnees) {
		this.orgAbonnees = orgAbonnees;
	}

	//Ajouter un abonné - Ménage
	public void addHhd(Household hhd) {
		this.hhdAbonnees.add(hhd);
		hhd.getCompanies().add(this);
	}

	//Retirer un abonné - Ménage
	public void removeHhd(long hhdId) {
		Household hhd = this.hhdAbonnees.stream().filter(t -> t.getId() == hhdId).findFirst().orElse(null);
		if (hhd != null) {
			this.hhdAbonnees.remove(hhd);
			hhd.getCompanies().remove(this);
		}
	}

	//Ajouter un abonné - Organisation
	public void addOrg(Organization org) {
		this.orgAbonnees.add(org);
		org.getCompanies().add(this);
	}

	//Retirer un abonné - Organisation
	public void removeOrg(long orgId) {
		Organization org = this.orgAbonnees.stream().filter(t -> t.getId() == orgId).findFirst().orElse(null);
		if (org != null) {
			this.orgAbonnees.remove(org);
			org.getCompanies().remove(this);
		}
	}
}
