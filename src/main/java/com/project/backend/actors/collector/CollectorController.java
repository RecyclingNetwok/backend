package com.project.backend.actors.collector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/collector")
public class CollectorController {
	
	private final CollectorService collectorService;
	
	@Autowired
	public CollectorController(CollectorService collectorService) {
		this.collectorService = collectorService;
	}
	
	@GetMapping("count")
	public Long Count() {
		return collectorService.count();
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
	public void addNewCollector(Collector collector) {
		collectorService.addNewCollector(collector);
	}
	
	@DeleteMapping
	public void deleteCollector(Long id) {
		collectorService.deleteCollector(id);
	}
	
}

