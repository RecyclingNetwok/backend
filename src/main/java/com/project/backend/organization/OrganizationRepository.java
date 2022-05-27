package com.project.backend.organization;

import org.springframework.transaction.annotation.Transactional;

import com.project.backend.login.repository.UserBaseRepository;

@Transactional
public interface OrganizationRepository extends UserBaseRepository<Organization>{
}
