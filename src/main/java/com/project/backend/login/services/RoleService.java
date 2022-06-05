package com.project.backend.login.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend.login.models.Role;
import com.project.backend.login.repository.RoleRepository;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public List<Role> getAllRoles() {
		return (List<Role>) roleRepository.findAll();
	}

	public Role getRoleByID(Long id) {
		return roleRepository.findById(id).get();
	}

	public void addNewRole(Role role) {
		roleRepository.save(role);
	}

	public void deleteRole(Long id) {
		boolean exists = roleRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Role with id '" + id + "' does not exists !");
		}
		roleRepository.deleteById(id);
		System.out.println("Deleting role...");
	}

	// TODO updateRole(Long id)
}

