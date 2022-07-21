package com.project.backend.actors.household;

import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.project.backend.actors.company.Company;
import com.project.backend.login.request.SignupRequest;


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
	
	@GetMapping("-{hhdID}/following")
	public List<Company> getCompaniesbyHouseholdId(@PathVariable(value = "hhdID") Long hhdID){
		return householdService.getCompaniesByHouseholdId(hhdID);
	}
	
	@PostMapping("-{hhdID}/follow/{comID}")
	public ResponseEntity<Household> abonner(@PathVariable("comID") Long comId,@PathVariable("hhdID")  Long hhdID){
		return householdService.abonner(comId, hhdID);
	}
	
	@DeleteMapping("-{hhdID}/unfollow/{comID}")
	public ResponseEntity<String> desabonner(@PathVariable("comID") Long comId,@PathVariable("hhdID") Long hhdId){
		return householdService.desabonner(comId, hhdId);
	}
	
	@PostMapping("/add")
	public void addNewHousehold(@RequestBody Household household) {
		householdService.addNewHousehold(household);
	}
	
	@DeleteMapping("-{HouseholdID}")
	public void deleteHousehold(@PathVariable("HouseholdID") Long id) {
		householdService.deleteHousehold(id);
	}
	
	@PutMapping
	public void update(@RequestBody Household h) {
		householdService.update(h);
	}
	
	@PutMapping("/update-all")
	public void UpdateAll(@RequestBody SignupRequest request) {
		householdService.update(request);
	}
	
	@PutMapping("/update-ava")
	public void UpdateAvatar(@RequestBody SignupRequest request) {
		householdService.updateAvatar(request);
	}
	
	@PutMapping("/update-pwd")
	public void UpdatePwd(@RequestBody SignupRequest request) {
		householdService.updatePwd(request);
	}
}
