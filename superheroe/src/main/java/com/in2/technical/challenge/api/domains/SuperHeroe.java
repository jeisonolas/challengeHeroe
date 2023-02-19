package com.in2.technical.challenge.api.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SuperHeroe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
    private String name;
   	private String publisher;
    private String alterEgo;
    private String firstAppearance;
    
	public long getId() {
		return id;
	}
	
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
