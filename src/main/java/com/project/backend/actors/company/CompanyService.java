package com.project.backend.actors.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend.actors.collector.Collector;


@Service
public class CompanyService {

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
	
	public void update(Company com){
		companyRepository.save(com);
	}
}
