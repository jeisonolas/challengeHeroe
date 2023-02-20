package com.in2.technical.challenge.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SuperHeroeResponse")
public class SuperHeroeResponse {

	@Schema(name = "name")
    private String name;
	
	@Schema(name = "publisher")
    private String publisher;
	
	@Schema(name = "alterEgo")
    private String alterEgo;
	
	@Schema(name = "firstAppearance")
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
