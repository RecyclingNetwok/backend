package com.project.backend.actors.organization;

import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.actors.household.Household;

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
	public void addNewOrganization(@RequestBody Organization organization) {
		organizationService.addNewOrganization(organization);
	}
	
	@DeleteMapping("-{OrganizationID}")
	public void deleteOrganization(@PathVariable("OrganizationID") Long id) {
		organizationService.deleteOrganization(id);
	}
	
	@PutMapping
	public void update(@RequestBody Organization o) {
		organizationService.update(o);
	}
}

