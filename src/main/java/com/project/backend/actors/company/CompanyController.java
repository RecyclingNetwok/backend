package com.project.backend.actors.company;

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

import com.project.backend.actors.collector.Collector;
import com.project.backend.login.request.SignupRequest;

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
	public void addNewCompany(@RequestBody Company company) {
		companyService.addNewCompany(company);
	}
	
	@DeleteMapping("-{CompanyID}")
	public void deleteCompany(@PathVariable("CompanyID") Long id) {
		companyService.deleteCompany(id);
	}
	
	@PutMapping("/update-all")
	public void UpdateAll(@RequestBody SignupRequest request) {
		companyService.update(request);
	}
	
	@PutMapping("/update-ava")
	public void UpdateAvatar(@RequestBody SignupRequest request) {
		companyService.updateAvatar(request);
	}
	
	@PutMapping("/update-pwd")
	public void UpdatePwd(@RequestBody SignupRequest request) {
		companyService.updatePwd(request);
	}
}
