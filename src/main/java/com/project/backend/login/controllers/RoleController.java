package com.project.backend.login.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.login.models.Role;
import com.project.backend.login.services.RoleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/login/roles")
public class RoleController {

private final RoleService roleService;
	
	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping
	public List<Role> getAllRoles(){
		return roleService.getAllRoles();
	}
	
	@GetMapping("-{RoleID}")
	public Role getRoleById(@PathVariable("RoleID") Long id) {
		return roleService.getRoleByID(id);
	}
	
	@PostMapping("/add")
	public void addNewRole(Role role) {
		roleService.addNewRole(role);
	}
	
	@DeleteMapping
	public void deleteRole(Long id) {
		roleService.deleteRole(id);
	}
	
}
