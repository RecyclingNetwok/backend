package com.project.backend.login.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.backend.login.models.ERole;
import com.project.backend.login.models.Role;
import com.project.backend.login.repository.RoleRepository;
import com.project.backend.services.category.Category;
import com.project.backend.services.category.CategoryRepository;
import com.project.backend.services.category.CategoryService;

@Component
public class Runner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Runner.class);

	private final RoleRepository roleRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	public Runner(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		String[] categories = { "Non Classé", "Plastique", "Plastique de couleur noire", "Papier bulle",
				"Dosettes de café", "Plastique compostable", "Tuyeaux d'arrosage", "Emballage de viande",
				"Emballage d'Arachides", "Sacs en plastique", "Bouchons et couvercles en plastique",
				"Rideaux de douche en plastique", "Ustensiles en plastique", "Emballage plastique et film",
				"Filtres à eau" };

		// Insert posts Categories
		Category c0 = new Category(categories[0]);
		if (categoryRepository.findByName(c0.getName()).isPresent()) {

		} else {

			for (String cat : categories) {
				Category c = new Category(cat);
				categoryRepository.save(c);
			}
		}

		// Insert Roles
		Role r1 = new Role(ERole.Administrateur);
		if (roleRepository.findByName(r1.getName()).isPresent()) {

		} else {
			for (ERole role : ERole.values()) {
				Role r = new Role(role);
				roleRepository.save(r);
			}
		}

	}

}
