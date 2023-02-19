package com.in2.technical.challenge.api.interfaces.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.in2.technical.challenge.api.domains.SuperHeroe;
import com.in2.technical.challenge.api.domains.dtos.SuperHeroeRequest;
import com.in2.technical.challenge.api.exceptions.ApiBussinessException;


@Service
public interface ISuperHeroeService {

	public List<SuperHeroe> getHeroes() throws ApiBussinessException;
	public List<SuperHeroe> getHeroesByName(String name) throws ApiBussinessException;
	public SuperHeroe getHeroeById(Long id) throws ApiBussinessException;
	public SuperHeroe addHeroe(SuperHeroeRequest request);
	public SuperHeroe updateHeroe(Long id,SuperHeroeRequest request) throws ApiBussinessException;
	public String deleteHeroe(Long id) throws ApiBussinessException;
}
