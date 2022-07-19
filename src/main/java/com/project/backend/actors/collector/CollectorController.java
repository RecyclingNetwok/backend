package com.project.backend.actors.collector;

import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.login.request.SignupRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/collector")
public class CollectorController {
	
	private final CollectorService collectorService;
	
	@Autowired
	public CollectorController(CollectorService collectorService) {
		this.collectorService = collectorService;
	}
	
	@GetMapping
	public List<Collector> getAllCollectors(){
		return collectorService.getAllCollectors();
	}
	
	@GetMapping("-{CollectorID}")
	public Collector getCollectorById(@PathVariable("CollectorID") Long id) {
		return collectorService.getCollectorByID(id);
	}
	
	@PostMapping("/add")
	public void addNewCollector(@RequestBody Collector collector) {
		collectorService.addNewCollector(collector);
	}
	
	@DeleteMapping("-{CollectorID}")
	public void deleteCollector(@PathVariable("CollectorID") Long id) {
		collectorService.deleteCollector(id);
	}
	
	@PutMapping
	public void update(@RequestBody Collector c) {
		collectorService.updateCollector(c);
	}
	
	@PutMapping("/update-all")
	public void UpdateAll(@RequestBody SignupRequest request) {
		collectorService.update(request);
	}
	
	@PutMapping("/update-ava")
	public void UpdateAvatar(@RequestBody SignupRequest request) {
		collectorService.updateAvatar(request);
	}
	
	@PutMapping("/update-pwd")
	public void UpdatePwd(@RequestBody SignupRequest request) {
		collectorService.updatePwd(request);
	}
}

