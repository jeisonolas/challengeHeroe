package com.in2.technical.challenge.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SuperHeroeRequest")
public class SuperHeroeRequest {

	@Schema(name = "name")
	@NotNull(message = "Name cannot be null")
	@Size(min = 3, max = 60, message = "Name must be between 3 and 60 characters")
    private String name;
	
	@Schema(name = "publisher")
	@NotNull(message = "Publisher cannot be null")
	@Size(min = 3, max = 60, message = "Publisher must be between 3 and 60 characters")
	private String publisher;
	
	@Schema(name = "alterEgo")
	@NotNull(message = "Alter ego cannot be null")
	@Size(min = 3, max = 60, message = "Alter ego must be between 3 and 60 characters")
    private String alterEgo;
	
	@Schema(name = "firstAppearance")
	@NotNull(message = "First Appearence cannot be null")
	@Size(min = 3, max = 60, message = "First Appearence must be between 3 and 60 characters")
    private String firstAppearance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAlterEgo() {
		return alterEgo;
	}

	public void setAlterEgo(String alterEgo) {
		this.alterEgo = alterEgo;
	}

	public String getFirstAppearance() {
		return firstAppearance;
	}

	public void setFirstAppearance(String firstAppearance) {
		this.firstAppearance = firstAppearance;
	}
}
