package com.project.backend.actors.household;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend.actors.company.Company;

@Service
public class HouseholdService {

	private final HouseholdRepository householdRepository;
	
	@Autowired
	public HouseholdService(HouseholdRepository householdRepository) {
		this.householdRepository = householdRepository;
	}
	
	public List<Household> getAllHouseholds(){
		return (List<Household>) householdRepository.findAll();
	}
	
	public Household getHouseholdByID(Long id) {
		return householdRepository.findById(id).get();
	}
	
	public void addNewHousehold(Household household) {
		Optional<Household> householdOptional= householdRepository.findByEmail(household.getEmail());
		if(householdOptional.isPresent()) {
			throw new IllegalStateException("Email Already Exist !");
		}else {
			System.out.println(household);
			householdRepository.save(household);
		}
	}
	
	public void deleteHousehold(Long id) {
		boolean exists = householdRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("Household with id '"+id+"' does not exists !");
		}
		householdRepository.deleteById(id);
		System.out.println("Deleting household...");
	}
	
	public void update(Household h){
		householdRepository.save(h);
	}
}

