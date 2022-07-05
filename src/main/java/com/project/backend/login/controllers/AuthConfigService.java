package com.project.backend.login.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.backend.actors.admin.Admin;
import com.project.backend.actors.admin.AdminRepository;
import com.project.backend.actors.collector.Collector;
import com.project.backend.actors.collector.CollectorRepository;
import com.project.backend.actors.company.Company;
import com.project.backend.actors.company.CompanyRepository;
import com.project.backend.actors.household.Household;
import com.project.backend.actors.household.HouseholdRepository;
import com.project.backend.actors.organization.Organization;
import com.project.backend.actors.organization.OrganizationRepository;
import com.project.backend.login.models.ERole;
import com.project.backend.login.models.Role;
import com.project.backend.login.repository.RoleRepository;
import com.project.backend.login.repository.UserRepository;
import com.project.backend.login.request.LoginRequest;
import com.project.backend.login.request.SignupRequest;
import com.project.backend.login.response.MessageResponse;
import com.project.backend.login.response.UserInfoResponse;
import com.project.backend.login.security.jwt.JwtUtils;
import com.project.backend.login.security.service.UserDetailsImpl;

@Service
public class AuthConfigService {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	HouseholdRepository householdRepository;
	@Autowired
	OrganizationRepository organizationRepository;
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	CollectorRepository collectorRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	
	public ResponseEntity<?> signIn (LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body(new UserInfoResponse(userDetails.getId(), userDetails.getUserType(), userDetails.getAvatarPath(), userDetails.getLogoPath(),
						userDetails.getShowCasePath(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}
	
	public ResponseEntity<?> signUp(SignupRequest signUpRequest){
		String user_type = signUpRequest.getUser_type();
		switch (user_type) {
		case "Menage":
			return registerHousehold(signUpRequest);
		case "Organisation":
			return registerOrganization(signUpRequest);
		case "Entreprise":
			return registerCompany(signUpRequest);
		case "Collecteur":
			return registerCollector(signUpRequest);
		default:
			return registerAdmin(signUpRequest);// Administrateur
		}
	}
	
	public ResponseEntity<?> signOut(){
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}
	
	private ResponseEntity<?> registerHousehold(SignupRequest signUpRequest) {
		if (householdRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Household's username is already taken!"));
		}
		if (householdRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Household's Email is already in use!"));
		}
		// Create new user's account
		Household household = new Household(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getAdress(), signUpRequest.getPhone(),
				signUpRequest.isVerified(), signUpRequest.getFamilyName(), signUpRequest.getAvatarPath());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		roles = setRoles(strRoles);

		household.setRoles(roles);
		householdRepository.save(household);
		return ResponseEntity.ok(new MessageResponse("Household registered successfully!"));

	}

	private ResponseEntity<?> registerOrganization(SignupRequest signUpRequest) {
		if (organizationRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Org's name is already taken!"));
		}
		if (organizationRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Org's Email is already in use!"));
		}
		// Create new org's account
		Organization organization = new Organization(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getAdress(), signUpRequest.getPhone(),
				signUpRequest.isVerified(), signUpRequest.getNIU(), signUpRequest.getLogoPath(),
				signUpRequest.getShowCasePath(), signUpRequest.getAvatarPath());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		roles = setRoles(strRoles);

		organization.setRoles(roles);
		organizationRepository.save(organization);
		return ResponseEntity.ok(new MessageResponse("Organization registered successfully!"));

	}

	private ResponseEntity<?> registerCompany(SignupRequest signUpRequest) {
		if (companyRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Org's name is already taken!"));
		}
		if (companyRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Org's Email is already in use!"));
		}
		// Create new org's account
		Company company = new Company(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getAdress(), signUpRequest.getPhone(),
				signUpRequest.isVerified(), signUpRequest.getNIU(), signUpRequest.getLogoPath(),
				signUpRequest.getShowCasePath(), signUpRequest.getAvatarPath());

		Set<String> strRoles = signUpRequest.getRole();
		System.out.println(strRoles);
		Set<Role> roles = new HashSet<>();

		roles = setRoles(strRoles);

		company.setRoles(roles);
		companyRepository.save(company);
		return ResponseEntity.ok(new MessageResponse("Company registered successfully!"));

	}

	private ResponseEntity<?> registerCollector(SignupRequest signUpRequest) {
		if (collectorRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Collector's name is already taken!"));
		}
		if (collectorRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Collector's Email is already in use!"));
		}
		// Create new collector's account
		Collector collector = new Collector(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getAdress(), signUpRequest.getPhone(),
				signUpRequest.isVerified(), signUpRequest.getAvatarPath());

		// Get company
		Optional<Company> optionalCom = companyRepository.findById(signUpRequest.getCom_id());
		if (!optionalCom.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		collector.setCom(optionalCom.get());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		roles = setRoles(strRoles);

		collector.setRoles(roles);
		collectorRepository.save(collector);
		return ResponseEntity.ok(new MessageResponse("Collector registered successfully!"));

	}

	private ResponseEntity<?> registerAdmin(SignupRequest signUpRequest) {
		if (adminRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Admin's Username is already taken!"));
		}
		if (adminRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Admin's Email is already in use!"));
		}
		// Create new admin's account
		Admin admin = new Admin(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getAdress(), signUpRequest.getPhone(),
				signUpRequest.isVerified(), signUpRequest.getAvatarPath());
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		roles = setRoles(strRoles);

		admin.setRoles(roles);
		adminRepository.save(admin);
		return ResponseEntity.ok(new MessageResponse("Admin registered successfully!"));
	}

	public Set<Role> setRoles(Set<String> strRoles) {
		Set<Role> roles = new HashSet<>();

		if (strRoles == null || (!strRoles.contains("Administrateur") && !strRoles.contains("Organisation")
				&& !strRoles.contains("Entreprise") && !strRoles.contains("Collecteur")
				&& !strRoles.contains("Menage"))) {
			throw new RuntimeException(
					"Role Not Found ! Please Enter a valid role :<br> 'admin' for Administrator<br> 'org' for Organization<br> "
							+ "'com' for Company<br> 'col' for Collctor <br> 'hhd' for Household ");
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "Administrateur":
					Role adminRole = roleRepository.findByName(ERole.Administrateur)
							.orElseThrow(() -> new RuntimeException("Error: Role ADMIN is not found."));
					roles.add(adminRole);
					break;
				case "Organisation":
					Role orgRole = roleRepository.findByName(ERole.Organisation)
							.orElseThrow(() -> new RuntimeException("Error: Role ORGANIZATION is not found."));
					roles.add(orgRole);
					break;
				case "Entreprise":
					Role comRole = roleRepository.findByName(ERole.Entreprise)
							.orElseThrow(() -> new RuntimeException("Error: Role COMPANY is not found."));
					roles.add(comRole);
					break;
				case "Collecteur":
					Role colRole = roleRepository.findByName(ERole.Collecteur)
							.orElseThrow(() -> new RuntimeException("Error: Role COLLECTOR is not found."));
					roles.add(colRole);
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.Menage)
							.orElseThrow(() -> new RuntimeException("Error: Role HOUSEHOLD is not found."));
					roles.add(userRole);
				}
			});
		}

		return roles;

	}
	
}


