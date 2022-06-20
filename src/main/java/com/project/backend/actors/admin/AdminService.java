package com.project.backend.actors.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	private final AdminRepository adminRepository;

	/**
	 * @param adminRepository
	 */
	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	public long count() {
		return adminRepository.count();
	}

	/**
	 * @return
	 */
	public List<Admin> getAllAdmins() {
		return (List<Admin>) adminRepository.findAll();
	}

	/**
	 * @param id
	 * @return
	 */
	public Admin getAdminByID(Long id) {
		return adminRepository.findById(id).get();
	}

	/**
	 * @param admin
	 */
	public void addNewAdmin(Admin admin) {
		Optional<Admin> adminOptional = adminRepository.findByEmail(admin.getEmail());
		if (adminOptional.isPresent()) {
			throw new IllegalStateException("Email Already Exist !");
		} else {
			System.out.println(admin);
			adminRepository.save(admin);
		}
	}

	/**
	 * @param id
	 */
	public void deleteAdmin(Long id) {
		boolean exists = adminRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Admin with id '" + id + "' does not exists !");
		}else {
			adminRepository.deleteById(id);
			System.out.println("Deleting admin...");
		}
	}

//	public void updateAdmin(Long id, Admin ad) {
//		Optional<Admin> exists = adminRepository.findById(id);
//		if (exists.isEmpty()) {
//			throw new IllegalStateException("Admin with id '" + id + "' does not exists !");
//		}else {
//			Admin admin = new Admin();
//			
//			admin.setUsername(ad.getUsername());
//			admin.setEmail(ad.getEmail());
//			admin.setPassword(exists.get().getPassword());
//			admin.setAdress(ad.getAdress());
//			admin.setPhone(ad.getPhone());
//			admin.setVerified(true);
//			adminRepository.save(admin);
//			System.out.println("Updating admin...");
//		}
//
//	}
	
}
