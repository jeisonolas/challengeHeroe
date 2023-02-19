package com.in2.technical.challenge.api.controllers;

import java.util.List;

import javax.validation.Valid;

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
import com.in2.technical.challenge.api.exceptions.ApiBussinessException;
import com.in2.technical.challenge.api.interfaces.services.ISuperHeroeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("heroes")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Heroes API", description = "This APi serve all functionality for management Heroes")
public class SuperHeroeController {

	@Autowired
	ISuperHeroeService service; 
	
	@GetMapping("/all")
	@MeasureExecutionTime
	@Operation(description = "Return all Heroes from database", summary ="Return all Heroes")
	//@Cacheable(value = "getHeroes", key = "#root.methodName")
	public ResponseEntity<List<SuperHeroe>> getHeroes() throws Exception, ApiBussinessException{
		
		return new ResponseEntity<>(service.getHeroes(),HttpStatus.OK);
	}
	
	@GetMapping("/ByName")
	@MeasureExecutionTime
	@Operation(description = "Return all Heroes by name from database", summary ="Return all Heroes by Name")
	public ResponseEntity<List<SuperHeroe>> getHeroesByName(@RequestParam("name") String name) throws ApiBussinessException{
				
		return new ResponseEntity<>(service.getHeroesByName(name),HttpStatus.OK);
	}
	
	@GetMapping("/ById")
	@MeasureExecutionTime
	@Operation(description = "Return Heroe by Id from database", summary ="Return Heroe By Id")
	public ResponseEntity<SuperHeroe> getHeroeById(@RequestParam("id") Long id) throws ApiBussinessException{
		return new ResponseEntity<>(service.getHeroeById(id),HttpStatus.OK);
	}
	
	
	@PostMapping()
	@MeasureExecutionTime
	@Operation(description = "Create new Heroe into database", summary ="Return Heroe created")
	public ResponseEntity<SuperHeroe> addHeroe(@Valid @RequestBody SuperHeroeRequest heroe){	
		return new ResponseEntity<>(service.addHeroe(heroe),HttpStatus.CREATED);
	}
	
	@PutMapping()
	@MeasureExecutionTime
	@Operation(description = "Update Heroe by Id into database", summary ="Return Heroe updated")
	public ResponseEntity<SuperHeroe> updateHeroe(@RequestParam("id") Long id,@Valid @RequestBody SuperHeroeRequest heroe) throws ApiBussinessException{
		return new ResponseEntity<>(service.updateHeroe(id, heroe),HttpStatus.CREATED);
	}
	
	@DeleteMapping()
	@MeasureExecutionTime
	@Operation(description = "Delete Heroe by Id from database", summary ="Return message with result")
	public ResponseEntity<String> deleteHeroe(@RequestParam("id") Long id) throws ApiBussinessException{
		return new ResponseEntity<>(service.deleteHeroe(id),HttpStatus.OK);
	}
}
