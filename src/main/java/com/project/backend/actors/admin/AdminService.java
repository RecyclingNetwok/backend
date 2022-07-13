package com.project.backend.actors.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	private final AdminRepository adminRepository;
	
	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	public List<Admin> getAllAdmins() {
		return (List<Admin>) adminRepository.findAll();
	}

	public Admin getAdminByID(Long id) {
		return adminRepository.findById(id).get();
	}

	public void addNewAdmin(Admin admin) {
		Optional<Admin> adminOptional = adminRepository.findByEmail(admin.getEmail());
		if (adminOptional.isPresent()) {
			throw new IllegalStateException("Email Already Exist !");
		} else {
			System.out.println(admin);
			adminRepository.save(admin);
		}
	}

	public void deleteAdmin(Long id) {
		boolean exists = adminRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Admin with id '" + id + "' does not exists !");
		}
		adminRepository.deleteById(id);
		System.out.println("Deleting admin...");
	}

	public void updateAdmin(Admin ad) {
		adminRepository.save(ad);
	}
}
