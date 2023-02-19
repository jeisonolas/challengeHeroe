package com.in2.technical.challenge.api.interfaces.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in2.technical.challenge.api.domains.SuperHeroe;
import com.in2.technical.challenge.api.domains.dtos.SuperHeroeRequest;
import com.in2.technical.challenge.api.interfaces.services.ISuperHeroeService;
import com.in2.technical.challenge.api.mappers.SuperHeroeMapper;
import com.in2.technical.challenge.api.repositories.ISuperHeroeRepository;

@Service
public class SuperHeroeService implements ISuperHeroeService {

	@Autowired
	ISuperHeroeRepository repository;

	@Override
	public List<SuperHeroe> getHeroes() {
		
		return  repository.findAll();
	}

	@Override
	public List<SuperHeroe> getHeroesByName(String name) {
		return  repository.findByNameContaining(name);
	}
	
	@Override
	public SuperHeroe getHeroeById(Long id) {
		
		Optional<SuperHeroe> heroe = repository.findById(id);
		
		return heroe.isPresent() ? heroe.get() : null;
	}
	
	@Override
	public SuperHeroe addHeroe(SuperHeroeRequest request) {
		
		SuperHeroe newHeroe = SuperHeroeMapper.INSTANCE.superHeroeRequestToSuperHeroe(request);
		
		return repository.save(newHeroe);
	}

	@Override
	public SuperHeroe updateHeroe(Long id, SuperHeroeRequest request) {
		
		Optional<SuperHeroe> superHeroeDB = repository.findById(id);
		SuperHeroe updatedHeroe = SuperHeroeMapper.INSTANCE.superHeroeRequestToSuperHeroe(request);
		
		if(superHeroeDB.isPresent()) {
			 	SuperHeroe heroe  = superHeroeDB.get();
	            
	            heroe.setAlterEgo(updatedHeroe.getAlterEgo());
	            heroe.setName(updatedHeroe.getName());
	            heroe.setFirstAppearance(updatedHeroe.getFirstAppearance());
	            heroe.setPublisher(updatedHeroe.getPublisher());
	            
	            repository.save(heroe);
	            
	            return heroe;
		}
		
		
		return null;
	}

	@Override
	public boolean deleteHeroe(Long id) {
		repository.deleteById(id);
		return true;
	}
}
