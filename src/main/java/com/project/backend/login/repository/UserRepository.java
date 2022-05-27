package com.project.backend.login.repository;

import org.springframework.transaction.annotation.Transactional;

import com.project.backend.login.models.User;

@Transactional
public interface UserRepository extends UserBaseRepository<User>{

}
