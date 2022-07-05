package com.project.backend.actors.admin;

import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.login.controllers.AuthConfigService;
import com.project.backend.login.controllers.AuthController;
import com.project.backend.login.request.SignupRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	private final AdminService adminService;
	
	@Autowired
	AuthConfigService configService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping
	public List<Admin> getAllAdmins(){
		return adminService.getAllAdmins();
	}
	
	@GetMapping("-{AdminID}")
	public Admin getAdminById(@PathVariable("AdminID") Long id) {
		return adminService.getAdminByID(id);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addNewAdmin(@RequestBody SignupRequest admin) {
		return configService.signUp(admin);
	}
	
	@DeleteMapping("-{AdminID}")
	public void deletePost(@PathVariable("AdminID") Long id) {
		adminService.deleteAdmin(id);
	}
	
}
