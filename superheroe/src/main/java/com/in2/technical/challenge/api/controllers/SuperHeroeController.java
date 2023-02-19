package com.in2.technical.challenge.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in2.technical.challenge.api.commons.annotations.MeasureExecutionTime;
import com.in2.technical.challenge.api.domains.SuperHeroe;
import com.in2.technical.challenge.api.domains.dtos.SuperHeroeRequest;
import com.in2.technical.challenge.api.interfaces.services.ISuperHeroeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("heroes")
@SecurityRequirement(name = "basicAuth")
public class SuperHeroeController {

	@Autowired
	ISuperHeroeService service; 
	
	@GetMapping("/all")
	@MeasureExecutionTime
	//@Cacheable(value = "getHeroes", key = "#root.methodName")
	public ResponseEntity<List<SuperHeroe>> getHeroes(){
		return new ResponseEntity<>(service.getHeroes(),HttpStatus.OK);
	}
	
	@GetMapping("/ById")
	@MeasureExecutionTime
	public ResponseEntity<SuperHeroe> getHeroeById(@RequestParam("id") Long id){
		return new ResponseEntity<>(service.getHeroeById(id),HttpStatus.OK);
	}
	@GetMapping("/ByName")
	@MeasureExecutionTime
	public ResponseEntity<List<SuperHeroe>> getHeroesByName(@RequestParam("name") String name){
				
		return new ResponseEntity<>(service.getHeroesByName(name),HttpStatus.OK);
	}
	
	@PostMapping()
	@MeasureExecutionTime
	public ResponseEntity<SuperHeroe> addHeroe(@RequestBody SuperHeroeRequest heroe){	
		return new ResponseEntity<>(service.addHeroe(heroe),HttpStatus.CREATED);
	}
	
	@PutMapping()
	@MeasureExecutionTime
	public ResponseEntity<SuperHeroe> updateHeroe(@RequestParam("id") Long id, @RequestBody SuperHeroeRequest heroe){
		return new ResponseEntity<>(service.updateHeroe(id, heroe),HttpStatus.CREATED);
	}
	
	@DeleteMapping()
	@MeasureExecutionTime
	public ResponseEntity<Boolean> deleteHeroe(@RequestParam("id") Long id){
		return new ResponseEntity<>(service.deleteHeroe(id),HttpStatus.OK);
	}
}
