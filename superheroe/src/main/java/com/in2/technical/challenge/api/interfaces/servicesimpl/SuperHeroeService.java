package com.in2.technical.challenge.api.interfaces.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in2.technical.challenge.api.commons.Validations;
import com.in2.technical.challenge.api.domains.SuperHeroe;
import com.in2.technical.challenge.api.domains.dtos.SuperHeroeRequest;
import com.in2.technical.challenge.api.exceptions.ApiBussinessException;
import com.in2.technical.challenge.api.interfaces.services.ISuperHeroeService;
import com.in2.technical.challenge.api.mappers.SuperHeroeMapper;
import com.in2.technical.challenge.api.repositories.ISuperHeroeRepository;

@Service
public class SuperHeroeService implements ISuperHeroeService {

	@Autowired
	ISuperHeroeRepository repository;

	@Override
	public List<SuperHeroe> getHeroes() throws ApiBussinessException {
		
		List<SuperHeroe> heroes = repository.findAll();
		
		Validations.validationList(heroes);
	
		return heroes ;
	}

	@Override
	public List<SuperHeroe> getHeroesByName(String name) throws ApiBussinessException {
		
		List<SuperHeroe> heroes = repository.findByNameContaining(name);
		
		Validations.validationList(heroes);
		
		return heroes;  
	}
	
	@Override
	public SuperHeroe getHeroeById(Long id) throws ApiBussinessException {
		
		Optional<SuperHeroe> heroe = repository.findById(id);
		
		Validations.validationObject(heroe);
		
		return heroe.get();
	}
	
	@Override
	public SuperHeroe addHeroe(SuperHeroeRequest request) {
		
		SuperHeroe newHeroe = SuperHeroeMapper.INSTANCE.superHeroeRequestToSuperHeroe(request);
		
		return repository.save(newHeroe);
	}

	@Override
	public SuperHeroe updateHeroe(Long id, SuperHeroeRequest request) throws ApiBussinessException {
		
		SuperHeroe superHeroeDB = this.getHeroeById(id);
		SuperHeroe updatedHeroe = SuperHeroeMapper.INSTANCE.superHeroeRequestToSuperHeroe(request);
			            
		superHeroeDB.setAlterEgo(updatedHeroe.getAlterEgo());
		superHeroeDB.setName(updatedHeroe.getName());
		superHeroeDB.setFirstAppearance(updatedHeroe.getFirstAppearance());
		superHeroeDB.setPublisher(updatedHeroe.getPublisher());
	            
		return repository.save(superHeroeDB);
	}

	@Override
	public String deleteHeroe(Long id) throws ApiBussinessException {
		
		SuperHeroe superHeroe= this.getHeroeById(id);
		
		repository.delete(superHeroe);
		
		return "Heroe was eliminated";
	}
}
