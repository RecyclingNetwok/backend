package com.project.backend.actors.admin;

import org.springframework.transaction.annotation.Transactional;

import com.project.backend.login.repository.UserBaseRepository;

@Transactional
public interface AdminRepository extends UserBaseRepository<Admin> {
}
