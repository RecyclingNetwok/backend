package com.project.backend.actors.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	private final AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("count")
	public Long Count() {
		return adminService.count();
	}
	
	@GetMapping
	public List<Admin> getAllAdmins(){
		return adminService.getAllAdmins();
	}
	
	@GetMapping("{AdminID}")
	public Admin getAdminById(@PathVariable("AdminID") Long id) {
		return adminService.getAdminByID(id);
	}
	
	@PostMapping("add")
	public void addNewAdmin(Admin admin) {
		adminService.addNewAdmin(admin);
	}
	
//	@PostMapping("update-{AdminID}")
//	public void updateAdmin(@PathVariable("AdminID") Long id, Admin ad) {
//		adminService.updateAdmin(id, ad);
//	}
	
	@DeleteMapping
	public void deleteAdmin(Long id) {
		adminService.deleteAdmin(id);
	}
	
}
