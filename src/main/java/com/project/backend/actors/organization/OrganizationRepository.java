package com.project.backend.actors.organization;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.project.backend.actors.company.Company;
import com.project.backend.login.repository.UserBaseRepository;

@Transactional
public interface OrganizationRepository extends UserBaseRepository<Organization>{
	
	List<Company> findCompanyById(Long id);
}
