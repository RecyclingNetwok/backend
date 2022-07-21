package com.project.backend.actors.organization;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.backend.actors.company.Company;
import com.project.backend.actors.company.CompanyRepository;
import com.project.backend.actors.organization.Organization;
import com.project.backend.actors.organization.Organization;
import com.project.backend.login.request.SignupRequest;

@Service
public class OrganizationService {

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	CompanyRepository companyRepository;
	
	private final OrganizationRepository organizationRepository;
	
	@Autowired
	public OrganizationService(OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}
	
	public List<Organization> getAllOrganizations(){
		return (List<Organization>) organizationRepository.findAll();
	}
	
	/**
	 * Get Companies By org Id
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Company> getCompaniesByOrganizationId(Long orgId) {
		if (!organizationRepository.existsById(orgId)) {
			throw new ResourceNotFoundException("Not Found Organization with id : " + orgId);
		}
		List<Company> companies = companyRepository.findOrgAbonneesById(orgId);
		return companies;
	}
	
	public ResponseEntity<Organization> abonner(Long comId, Long orgID) {
			long orgId = orgID;
			Company company = companyRepository.findById(comId)
					.orElseThrow( () -> new ResourceNotFoundException("Not Found Company with id : "+comId) );

				Organization _organization = organizationRepository.findById(orgId)
						.orElseThrow( () -> new ResourceNotFoundException("Not Found Organization With id : "+orgId) );
				company.addOrg(_organization);
				companyRepository.save(company);
		return new ResponseEntity<Organization>(_organization, HttpStatus.OK);
	}

	public ResponseEntity<String> desabonner(Long comId, Long orgId){
		Company company = companyRepository.findById(comId)
				.orElseThrow( () -> new ResourceNotFoundException("Not Found Company With id : "+comId) );
		
		company.removeOrg(orgId);
		companyRepository.save(company);
		return new ResponseEntity<String>("Organisation désabonné avec Succès !",HttpStatus.OK);
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
