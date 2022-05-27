package com.project.backend.collector;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectorService {

private final CollectorRepository collectorRepository;
	
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
	
	//TODO updateCollector(Long id)
}

