package com.in2.technical.challenge.infraestructure.mappers;

import java.util.List;

import com.in2.technical.challenge.domain.entities.SuperHeroe;
import com.in2.technical.challenge.domain.dtos.SuperHeroeRequest;
import com.in2.technical.challenge.domain.dtos.SuperHeroeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SuperHeroeMapper {

	SuperHeroeMapper INSTANCE = Mappers.getMapper(SuperHeroeMapper.class);
	
	SuperHeroe superHeroeRequestToSuperHeroe(SuperHeroeRequest source);
	
	SuperHeroeRequest superHeroeToSuperHeroeRequest(SuperHeroe source);
	
	List<SuperHeroe> superHeroeRequestListToSuperHeroeList(List<SuperHeroeRequest> source);
	
	List<SuperHeroeRequest> superHeroeListToSuperHeroeRequestList(List<SuperHeroe> source);
	
	SuperHeroe superHeroeResponseToSuperHeroe(SuperHeroeResponse source);
	
	SuperHeroeResponse superHeroeToSuperHeroeResponse(SuperHeroe source);
	
	List<SuperHeroe> superHeroeResponseListToSuperHeroeList(List<SuperHeroeResponse> source);
	
	List<SuperHeroeResponse> superHeroeListToSuperHeroeResponseList(List<SuperHeroe> source);
}
