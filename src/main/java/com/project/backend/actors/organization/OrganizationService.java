package com.project.backend.actors.organization;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.backend.actors.organization.Organization;
import com.project.backend.login.request.SignupRequest;

@Service
public class OrganizationService {

	@Autowired
	PasswordEncoder encoder;
	
	private final OrganizationRepository organizationRepository;
	
	@Autowired
	public OrganizationService(OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
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
	
	public void update (Organization org) {
		organizationRepository.save(org);
	}
	
	
	public void updateAvatar(SignupRequest request) {
		Organization ad = organizationRepository.findById(request.getId()).get();
		ad.setAvatarPath(request.getAvatarPath());
		organizationRepository.save(ad);
	}
	
	public void updatePwd( SignupRequest request) {
		Organization ad = organizationRepository.findById(request.getId()).get();
		ad.setAvatarPath(encoder.encode(request.getPassword()));
		organizationRepository.save(ad);
	}
	
	public void update(SignupRequest request) {
		Organization ad = organizationRepository.findById(request.getId()).get();
		
		ad.setUsername(request.getUsername());
		ad.setEmail(request.getEmail());
		ad.setAdress(request.getAdress());
		ad.setPhone(request.getPhone());
		ad.setNIU(request.getNIU());
		
		organizationRepository.save(ad);
	}
}
