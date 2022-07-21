package com.project.backend.actors.household;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.backend.actors.company.Company;
import com.project.backend.actors.company.CompanyRepository;
import com.project.backend.login.request.SignupRequest;

@Service
public class HouseholdService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	CompanyRepository companyRepository;

	private final HouseholdRepository householdRepository;

	@Autowired
	public HouseholdService(HouseholdRepository householdRepository) {
		this.householdRepository = householdRepository;
	}

	/**
	 * Get All
	 * 
	 * @return
	 */
	public List<Household> getAllHouseholds() {
		return (List<Household>) householdRepository.findAll();
	}

	/**
	 * Get All By Company Id
	 * 
	 * @param comId
	 * @return
	 */
	public List<Company> getAllByCompany(Long hhdId) {
		if (!companyRepository.existsById(hhdId)) {
			throw new ResourceNotFoundException("Not Found Company with id : " + hhdId);
		}
		List<Company> companies = householdRepository.findCompanyById(hhdId);
		return companies;
	}

	/**
	 * Get By Id
	 * 
	 * @param id
	 * @return
	 */
	public Household getHouseholdByID(Long id) {
		return householdRepository.findById(id).get();
	}

	/**
	 * Get Companies By Hhd Id
	 * 
	 * @param hhdId
	 * @return
	 */
	public List<Company> getCompaniesByHouseholdId(Long hhdId) {
		if (!householdRepository.existsById(hhdId)) {
			throw new ResourceNotFoundException("Not Found Household with id : " + hhdId);
		}
		List<Company> companies = companyRepository.findHhdAbonneesById(hhdId);
		return companies;
	}

	public void addNewHousehold(Household household) {
		Optional<Household> householdOptional = householdRepository.findByEmail(household.getEmail());
		if (householdOptional.isPresent()) {
			throw new IllegalStateException("Email Already Exist !");
		} else {
			System.out.println(household);
			householdRepository.save(household);
		}
	}

	public ResponseEntity<Household> abonner(Long comId, Long hhdID) {
			long hhdId = hhdID;
			Company company = companyRepository.findById(comId)
					.orElseThrow( () -> new ResourceNotFoundException("Not Found Company with id : "+comId) );

			//Household exited
				Household _household = householdRepository.findById(hhdId)
						.orElseThrow( () -> new ResourceNotFoundException("Not Found Household With id : "+hhdId) );
				company.addHhd(_household);
				companyRepository.save(company);
		return new ResponseEntity<Household>(_household, HttpStatus.OK);
	}

	public ResponseEntity<String> desabonner(Long comId, Long hhdId){
		Company company = companyRepository.findById(comId)
				.orElseThrow( () -> new ResourceNotFoundException("Not Found Company With id : "+comId) );
		
		company.removeHhd(hhdId);
		companyRepository.save(company);
		return new ResponseEntity<String>("Ménage désabonné avec Succès !", HttpStatus.OK);
	}
	
	public void deleteHousehold(Long id) {
		boolean exists = householdRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Household with id '" + id + "' does not exists !");
		}
		householdRepository.deleteById(id);
		System.out.println("Deleting household...");
	}

	public void update(Household h) {
		householdRepository.save(h);
	}

	public void updateAvatar(SignupRequest request) {
		Household ad = householdRepository.findById(request.getId()).get();
		ad.setAvatarPath(request.getAvatarPath());
		householdRepository.save(ad);
	}

	public void updatePwd(SignupRequest request) {
		Household ad = householdRepository.findById(request.getId()).get();
		ad.setAvatarPath(encoder.encode(request.getPassword()));
		householdRepository.save(ad);
	}

	public void update(SignupRequest request) {
		Household ad = householdRepository.findById(request.getId()).get();

		ad.setUsername(request.getUsername());
		ad.setEmail(request.getEmail());
		ad.setAdress(request.getAdress());
		ad.setPhone(request.getPhone());
		ad.setFamilyName(request.getFamilyName());

		householdRepository.save(ad);
	}
}
