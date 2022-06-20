package com.project.backend.login.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.backend.login.models.ERole;
import com.project.backend.login.models.Role;
import com.project.backend.login.repository.RoleRepository;

@Component
public class Runner implements CommandLineRunner {

//	private static final Logger logger = LoggerFactory.getLogger(Runner.class);
	
	private final RoleRepository roleRepository;

	@Autowired
	public Runner(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Role r1 = new Role(ERole.ROLE_ADMIN);
		Role r2 = new Role(ERole.ROLE_COMPANY);
		Role r3 = new Role(ERole.ROLE_ORGANIZATION);
		Role r4 = new Role(ERole.ROLE_HOUSEHOLD);
		
		Optional<Role> r5 = roleRepository.findByName(ERole.ROLE_ADMIN);
		
		if(r5.isEmpty()) {
			roleRepository.save(r1);
			roleRepository.save(r2);
			roleRepository.save(r3);
			roleRepository.save(r4);
		}
	}

}
