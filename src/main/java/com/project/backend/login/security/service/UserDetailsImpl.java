package com.project.backend.login.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.backend.actors.admin.Admin;
import com.project.backend.actors.collector.Collector;
import com.project.backend.actors.company.Company;
import com.project.backend.actors.household.Household;
import com.project.backend.actors.organization.Organization;
import com.project.backend.login.models.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Long id;
	
	private String userType;
	
	private String avatarPath;
	private String logoPath;
	private String showCasePath;
	
	private String username;
	private String email;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	

	public UserDetailsImpl(Long id, String type, String avatarPath, String logoPath, String showCasePath, String username,
			String email, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.userType = type;
		this.avatarPath = avatarPath;
		this.logoPath = logoPath;
		this.showCasePath = showCasePath;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public UserDetailsImpl(Long id, String type, String avatarPath, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.userType = type;
		this.avatarPath = avatarPath;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public UserDetailsImpl(Long id, String type,  String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.userType = type;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		
		String[] details = getOtherUserDetails(user);
		
		return new UserDetailsImpl(user.getId(), details[0], details[1], details[2], details[3],  user.getUsername(), user.getEmail(), user.getPassword(), authorities);
	}

	public static String[] getOtherUserDetails(User user) {
		if(user instanceof Admin) {
			Admin ad = (Admin) user;
			
			String [] details = {"Administrateur", ad.getAvatarPath(), "", ""};
			return details;
		}else if(user instanceof Household) {
			Household h = (Household) user;
			String [] details = {"Menage", h.getAvatarPath(), "", ""};
			return details;
			
		}else if(user instanceof Organization) {
			Organization org = (Organization) user;
			String [] details = {"Organisation", org.getAvatarPath(), org.getLogoPath(), org.getShowCasePath()};
			return details;
			
		}else if(user instanceof Company) {
			Company c = (Company) user;
			String [] details = {"Entreprise", c.getAvatarPath(), c.getLogoPath(), c.getShowCasePath()};
			return details;
			
		}else if(user instanceof Collector) {
			Collector col = (Collector) user;
			String [] details = {"Collecteur", col.getAvatarPath(), "", ""};
			return details;
			
		}
		
		return null;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}
	
	public String getUserType() {
		return userType;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public String getShowCasePath() {
		return showCasePath;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

}
