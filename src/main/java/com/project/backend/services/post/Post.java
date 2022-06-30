package com.project.backend.services.post;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.project.backend.actors.company.Company;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long com_id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Company company;
	
	private String title;
	
	private String description;
	
	private String imagePath;
	
	private String imageTitle;
	
	@CreationTimestamp
	private LocalDate createOn;
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Post(String title, Long com_id, String description, String imagePath, String imageTitle,
			LocalDate createOn) {
		super();
		this.com_id = com_id;
		this.title = title;
		this.description = description;
		this.imagePath = imagePath;
		this.imageTitle = imageTitle;
		this.createOn = createOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}

	public LocalDate getCreateOn() {
		return createOn;
	}

	public void setCreateOn(LocalDate createOn) {
		this.createOn = createOn;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Long getCom_id() {
		return com_id;
	}

	public void setCom_id(Long com_id) {
		this.com_id = com_id;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", company=" + company + ", title=" + title + ", description=" + description
				+ ", imagePath=" + imagePath + ", imageTitle=" + imageTitle + ", createOn=" + createOn + "]";
	}
	
}
