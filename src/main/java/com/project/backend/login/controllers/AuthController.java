package com.project.backend.login.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.actors.admin.AdminRepository;
import com.project.backend.actors.collector.CollectorRepository;
import com.project.backend.actors.company.CompanyRepository;
import com.project.backend.actors.household.HouseholdRepository;
import com.project.backend.actors.organization.OrganizationRepository;
import com.project.backend.login.repository.RoleRepository;
import com.project.backend.login.repository.UserRepository;
import com.project.backend.login.request.LoginRequest;
import com.project.backend.login.request.SignupRequest;
import com.project.backend.login.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthConfigService configService;

	@PostMapping(path = "/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return configService.signIn(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return configService.signUp(signUpRequest);
	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		return configService.signOut();
	}
}
