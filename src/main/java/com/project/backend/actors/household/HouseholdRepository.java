package com.project.backend.actors.household;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.project.backend.actors.company.Company;
import com.project.backend.actors.organization.Organization;
import com.project.backend.login.repository.UserBaseRepository;

@Transactional
public interface HouseholdRepository extends UserBaseRepository<Household> {
	
	List<Company> findCompanyById(Long id);
}
