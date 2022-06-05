package com.project.backend.actors.company;

import org.springframework.transaction.annotation.Transactional;

import com.project.backend.login.repository.UserBaseRepository;

@Transactional
public interface CompanyRepository extends UserBaseRepository<Company>{
}
