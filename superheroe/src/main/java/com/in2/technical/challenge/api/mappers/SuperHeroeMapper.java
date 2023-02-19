package com.in2.technical.challenge.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.in2.technical.challenge.api.domains.SuperHeroe;
import com.in2.technical.challenge.api.domains.dtos.SuperHeroeRequest;
import com.in2.technical.challenge.api.domains.dtos.SuperHeroeResponse;

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
