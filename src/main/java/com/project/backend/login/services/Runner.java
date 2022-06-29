package com.project.backend.login.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.backend.login.models.ERole;
import com.project.backend.login.models.Role;
import com.project.backend.login.repository.RoleRepository;

@Component
public class Runner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Runner.class);
	
	private final RoleRepository roleRepository;

	@Autowired
	public Runner(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Role r1 = new Role(ERole.ROLE_ADMIN);
		Role r2 = new Role(ERole.ROLE_HOUSEHOLD);
		Role r3 = new Role(ERole.ROLE_COMPANY);
		Role r4 = new Role(ERole.ROLE_COLLECTOR);
		Role r5 = new Role(ERole.ROLE_ORGANIZATION);
		
		if ( roleRepository.findByName(r1.getName() ).isEmpty() ) {
			roleRepository.save(r1);
			roleRepository.save(r2);
			roleRepository.save(r3);
			roleRepository.save(r4);
			roleRepository.save(r5);
		}
		
		
	}

}
