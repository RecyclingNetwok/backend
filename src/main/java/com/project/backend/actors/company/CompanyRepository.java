package com.project.backend.actors.company;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.project.backend.login.repository.UserBaseRepository;

@Transactional
public interface CompanyRepository extends UserBaseRepository<Company> {
	List<Company> findHhdAbonneesById(Long id);

	List<Company> findOrgAbonneesById(Long id);
}
