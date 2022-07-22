package com.project.backend.services.post;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.project.backend.actors.company.Company;
import com.project.backend.services.category.Category;

@Entity
@DynamicUpdate
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long com_id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Company company;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "post_categories", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	private String title;

	private String description;

	private String imagePath;

	private String imageTitle;

	private String meta_description;

	private String meta_title;

	private String content;

	private boolean published;

	@CreationTimestamp
	private LocalDate createOn;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(Long com_id, Company company, Set<Category> categories, String title, String description,
			String imagePath, String imageTitle, String meta_description, String meta_title, String content,
			boolean published) {
		super();
		this.com_id = com_id;
		this.company = company;
		this.categories = categories;
		this.title = title;
		this.description = description;
		this.imagePath = imagePath;
		this.imageTitle = imageTitle;
		this.meta_description = meta_description;
		this.meta_title = meta_title;
		this.content = content;
		this.published = published;
		this.createOn = LocalDate.now();
	}

	public Post(String title, Long com_id, String description, String imagePath, String imageTitle) {
		super();
		this.com_id = com_id;
		this.title = title;
		this.description = description;
		this.imagePath = imagePath;
		this.imageTitle = imageTitle;
		this.createOn = LocalDate.now();
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

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Long getCom_id() {
		return com_id;
	}

	public void setCom_id(Long com_id) {
		this.com_id = com_id;
	}

	public String getMeta_description() {
		return meta_description;
	}

	public void setMeta_description(String meta_description) {
		this.meta_description = meta_description;
	}

	public String getMeta_title() {
		return meta_title;
	}

	public void setMeta_title(String meta_title) {
		this.meta_title = meta_title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", com_id=" + com_id + ", company=" + company + ", categories=" + categories
				+ ", title=" + title + ", description=" + description + ", imagePath=" + imagePath + ", imageTitle="
				+ imageTitle + ", meta_description=" + meta_description + ", meta_title=" + meta_title + ", content="
				+ content + ", published=" + published + ", createOn=" + createOn + "]";
	}

}
