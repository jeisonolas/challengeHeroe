package com.in2.technical.challenge.application;

import com.in2.technical.challenge.domain.interfaces.ISuperHeroeService;
import com.in2.technical.challenge.infraestructure.utilities.Validations;
import com.in2.technical.challenge.domain.enums.ApiErrorType;
import com.in2.technical.challenge.domain.entities.SuperHeroe;
import com.in2.technical.challenge.domain.dtos.SuperHeroeRequest;
import com.in2.technical.challenge.domain.exceptions.ApiBussinessException;
import com.in2.technical.challenge.infraestructure.mappers.SuperHeroeMapper;
import com.in2.technical.challenge.domain.repositories.ISuperHeroeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroeService implements ISuperHeroeService {


	private final ISuperHeroeRepository repository;
	public SuperHeroeService(ISuperHeroeRepository repository){
		this.repository = repository;
	}

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
	public SuperHeroe addHeroe(SuperHeroeRequest request) throws ApiBussinessException {
		
		SuperHeroe newHeroe = SuperHeroeMapper.INSTANCE.superHeroeRequestToSuperHeroe(request);
		SuperHeroe heroe = repository.save(newHeroe);

		if(heroe.getId()<=0) throw new ApiBussinessException(ApiErrorType.NOT_CREATED);

		return heroe;
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
