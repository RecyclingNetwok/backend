package com.project.backend.actors.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.backend.actors.company.Company;
import com.project.backend.actors.collector.Collector;
import com.project.backend.login.request.SignupRequest;


@Service
public class CompanyService {
	
	@Autowired
	PasswordEncoder encoder;
	
	private final CompanyRepository companyRepository;
	
	@Autowired
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	public List<Company> getAllCompanys(){
		return (List<Company>) companyRepository.findAll();
	}
	
	public Company getCompanyByID(Long id) {
		return companyRepository.findById(id).get();
	}
	
	public void addNewCompany(Company company) {
		Optional<Company> companyOptional= companyRepository.findByEmail(company.getEmail());
		if(companyOptional.isPresent()) {
			throw new IllegalStateException("Email Already Exist !");
		}else {
			System.out.println(company);
			companyRepository.save(company);
		}
	}
	
	public void deleteCompany(Long id) {
		boolean exists = companyRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("Company with id '"+id+"' does not exists !");
		}
		companyRepository.deleteById(id);
		System.out.println("Deleting company...");
	}
	
	public void updateAvatar(SignupRequest request) {
		Company ad = companyRepository.findById(request.getId()).get();
		ad.setAvatarPath(request.getAvatarPath());
		companyRepository.save(ad);
	}
	
	public void updatePwd( SignupRequest request) {
		Company ad = companyRepository.findById(request.getId()).get();
		ad.setAvatarPath(encoder.encode(request.getPassword()));
		companyRepository.save(ad);
	}
	
	public void update(SignupRequest request) {
		Company ad = companyRepository.findById(request.getId()).get();
		
		ad.setUsername(request.getUsername());
		ad.setEmail(request.getEmail());
		ad.setAdress(request.getAdress());
		ad.setPhone(request.getPhone());
		ad.setNIU(request.getNIU());
		
		companyRepository.save(ad);
	}
	
	public void updateVerified(SignupRequest request) {
		Company ad = companyRepository.findById(request.getId()).get();
		
		if(request.isVerified()) {
			throw new ResourceNotFoundException("Company with id : "+request.getId()+" Already verified !");
		}
		ad.setVerified(request.isVerified());
		companyRepository.save(ad);
	}
	
	public List<Company> getVerifiedCompanies() {
		return companyRepository.findByVerified();
	}
	
	public List<Company> getNotVerifiedCompanies() {
		return companyRepository.findByNotVerified();
	}

}
