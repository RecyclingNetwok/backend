package com.project.backend.login.response;

import java.util.List;

public class UserInfoResponse {
	private Long id;
	private String userType;
	private String avatarPath;
	private String logoPath;
	private String showCasePath;
	private String username;
	private String email;
	private List<String> roles;
	
	public UserInfoResponse(Long id, String type, String avatarPath, String logoPath, String showCasePath, String username,
			String email, List<String> roles) {
		super();
		this.id = id;
		this.userType = type;
		this.avatarPath = avatarPath;
		this.logoPath = logoPath;
		this.showCasePath = showCasePath;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public UserInfoResponse(Long id, String type, String avatarPath, String username, String email, List<String> roles) {
		super();
		this.id = id;
		this.userType = type;
		this.avatarPath = avatarPath;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}


	public UserInfoResponse(Long id, String type, String username, String email, List<String> roles) {
		this.id = id;
		this.userType = type;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getShowCasePath() {
		return showCasePath;
	}

	public void setShowCasePath(String showCasePath) {
		this.showCasePath = showCasePath;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}