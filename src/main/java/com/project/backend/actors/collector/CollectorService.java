package com.project.backend.actors.collector;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.backend.login.request.SignupRequest;

@Service
public class CollectorService {

private final CollectorRepository collectorRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	public CollectorService(CollectorRepository collectorRepository) {
		this.collectorRepository = collectorRepository;
	}
	
	public List<Collector> getAllCollectors(){
		return (List<Collector>) collectorRepository.findAll();
	}
	
	public Collector getCollectorByID(Long id) {
		return collectorRepository.findById(id).get();
	}
	
	public void addNewCollector(Collector collector) {
		Optional<Collector> collectorOptional= collectorRepository.findByEmail(collector.getEmail());
		if(collectorOptional.isPresent()) {
			throw new IllegalStateException("Email Already Exist !");
		}else {
			System.out.println(collector);
			collectorRepository.save(collector);
		}
	}
	
	public void deleteCollector(Long id) {
		boolean exists = collectorRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("Collector with id '"+id+"' does not exists !");
		}
		collectorRepository.deleteById(id);
		System.out.println("Deleting collector...");
	}
	
	public void updateCollector(Collector c){
		collectorRepository.save(c);
	}
	
	public void updateAvatar(SignupRequest request) {
		Collector ad = collectorRepository.findById(request.getId()).get();
		ad.setAvatarPath(request.getAvatarPath());
		collectorRepository.save(ad);
	}
	
	public void updatePwd( SignupRequest request) {
		Collector ad = collectorRepository.findById(request.getId()).get();
		ad.setAvatarPath(encoder.encode(request.getPassword()));
		collectorRepository.save(ad);
	}
	
	public void update(SignupRequest request) {
		Collector ad = collectorRepository.findById(request.getId()).get();
		
		ad.setUsername(request.getUsername());
		ad.setEmail(request.getEmail());
		ad.setAdress(request.getAdress());
		ad.setPhone(request.getPhone());
		
		collectorRepository.save(ad);
	}

}

