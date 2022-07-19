package com.project.backend.actors.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.backend.login.request.SignupRequest;

@Service
public class AdminService {

	private final AdminRepository adminRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
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

	public void updateAdmin(Admin admin) {
		adminRepository.save(admin);
	}
	
	public void updateAvatar(SignupRequest request) {
		Admin ad = adminRepository.findById(request.getId()).get();
		ad.setAvatarPath(request.getAvatarPath());
		adminRepository.save(ad);
	}
	
	public void updatePwd( SignupRequest request) {
		Admin ad = adminRepository.findById(request.getId()).get();
		ad.setAvatarPath(encoder.encode(request.getPassword()));
		adminRepository.save(ad);
	}
	
	public void update(SignupRequest request) {
		Admin ad = adminRepository.findById(request.getId()).get();
		
		ad.setUsername(request.getUsername());
		ad.setEmail(request.getEmail());
		ad.setAdress(request.getAdress());
		ad.setPhone(request.getPhone());
		
		adminRepository.save(ad);
	}

}
