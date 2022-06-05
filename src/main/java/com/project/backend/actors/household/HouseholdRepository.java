package com.project.backend.actors.household;

import org.springframework.transaction.annotation.Transactional;

import com.project.backend.login.repository.UserBaseRepository;

@Transactional
public interface HouseholdRepository extends UserBaseRepository<Household>{
}
