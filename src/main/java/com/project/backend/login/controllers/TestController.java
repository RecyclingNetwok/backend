package com.project.backend.login.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/household")
	@PreAuthorize("hasAuthority('Menage')")	
	public String householdAccess() {
		return "Household Content.";
	}

	@GetMapping("/org")
	@PreAuthorize("hasAuthority('Organisation')")
	public String orgAccess() {
		return "Organization Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasAuthority('Entreprise')")
	public String companyAccess() {
		return "COMPANY Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('Administrateur')")
	public String adminAccess() {
		return "Admin Board.";
	}
}