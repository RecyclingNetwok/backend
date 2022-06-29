package com.project.backend.household;

import org.springframework.transaction.annotation.Transactional;

import com.project.backend.login.repository.UserBaseRepository;

@Transactional
public interface HouseholdRepository extends UserBaseRepository<Household>{
}
