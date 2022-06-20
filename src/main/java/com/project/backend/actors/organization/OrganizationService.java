package com.project.backend.actors.organization;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

	private final OrganizationRepository organizationRepository;
	
	@Autowired
	public OrganizationService(OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public long count() {
		return organizationRepository.count();
	}
	
	public List<Organization> getAllOrganizations(){
		return (List<Organization>) organizationRepository.findAll();
	}
	
	public Organization getOrganizationByID(Long id) {
		return organizationRepository.findById(id).get();
	}
	
	public void addNewOrganization(Organization organization) {
		Optional<Organization> organizationOptional= organizationRepository.findByEmail(organization.getEmail());
		if(organizationOptional.isPresent()) {
			throw new IllegalStateException("Email Already Exist !");
		}else {
			System.out.println(organization);
			organizationRepository.save(organization);
		}
	}
	
	public void deleteOrganization(Long id) {
		boolean exists = organizationRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("Organization with id '"+id+"' does not exists !");
		}
		organizationRepository.deleteById(id);
		System.out.println("Deleting organization...");
	}
	
	//TODO updateOrganization(Long id)
}
