package com.project.backend.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
	
	private final CompanyService companyService;
	
	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping
	public List<Company> getAllCompanys(){
		return companyService.getAllCompanys();
	}
	
	@GetMapping("-{CompanyID}")
	public Company getCompanyById(@PathVariable("CompanyID") Long id) {
		return companyService.getCompanyByID(id);
	}
	
	@PostMapping("/add")
	public void addNewCompany(Company company) {
		companyService.addNewCompany(company);
	}
	
	@DeleteMapping
	public void deleteCompany(Long id) {
		companyService.deleteCompany(id);
	}
	
}
