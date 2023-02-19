package com.in2.technical.challenge.api.interfaces.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.in2.technical.challenge.api.domains.SuperHeroe;
import com.in2.technical.challenge.api.domains.dtos.SuperHeroeRequest;


@Service
public interface ISuperHeroeService {

	public List<SuperHeroe> getHeroes();
	public List<SuperHeroe> getHeroesByName(String name);
	public SuperHeroe getHeroeById(Long id);
	public SuperHeroe addHeroe(SuperHeroeRequest request);
	public SuperHeroe updateHeroe(Long id,SuperHeroeRequest request);
	public boolean deleteHeroe(Long id);
}
