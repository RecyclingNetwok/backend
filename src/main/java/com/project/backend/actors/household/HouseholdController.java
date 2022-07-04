package com.project.backend.actors.household;

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


//@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/household")
public class HouseholdController {
	
	private final HouseholdService householdService;
	
	@Autowired
	public HouseholdController(HouseholdService householdService) {
		this.householdService = householdService;
	}
	
	@GetMapping
	public List<Household> getAllHouseholds(){
		return householdService.getAllHouseholds();
	}
	
	@GetMapping("-{HouseholdID}")
	public Household getHouseholdById(@PathVariable("HouseholdID") Long id) {
		return householdService.getHouseholdByID(id);
	}
	
	@PostMapping("/add")
	public void addNewHousehold(Household household) {
		householdService.addNewHousehold(household);
	}
	
	@DeleteMapping("-{HouseholdID}")
	public void deleteHousehold(@PathVariable("HouseholdID") Long id) {
		householdService.deleteHousehold(id);
	}
	
}
