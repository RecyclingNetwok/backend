package com.project.backend.collector;

import org.springframework.transaction.annotation.Transactional;

import com.project.backend.login.repository.UserBaseRepository;

@Transactional
public interface CollectorRepository extends UserBaseRepository<Collector>{
}
