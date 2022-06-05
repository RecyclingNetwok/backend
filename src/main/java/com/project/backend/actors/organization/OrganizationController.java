package com.project.backend.actors.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/org")
public class OrganizationController {
	
	private final OrganizationService organizationService;
	
	@Autowired
	public OrganizationController(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	@GetMapping
	public List<Organization> getAllOrganizations(){
		return organizationService.getAllOrganizations();
	}
	
	@GetMapping("-{OrganizationID}")
	public Organization getOrganizationById(@PathVariable("OrganizationID") Long id) {
		return organizationService.getOrganizationByID(id);
	}
	
	@PostMapping("/add")
	public void addNewOrganization(Organization organization) {
		organizationService.addNewOrganization(organization);
	}
	
	@DeleteMapping
	public void deleteOrganization(Long id) {
		organizationService.deleteOrganization(id);
	}
	
}

