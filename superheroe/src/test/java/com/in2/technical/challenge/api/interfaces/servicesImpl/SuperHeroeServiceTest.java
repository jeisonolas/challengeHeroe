package com.in2.technical.challenge.api.interfaces.servicesImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.in2.technical.challenge.api.domains.SuperHeroe;
import com.in2.technical.challenge.api.domains.dtos.SuperHeroeRequest;
import com.in2.technical.challenge.api.exceptions.ApiBussinessException;
import com.in2.technical.challenge.api.interfaces.services.ISuperHeroeService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest class SuperHeroeServiceTest {

	@Autowired
	private ISuperHeroeService service;
	
	@Test
	void getHeroesTest() throws ApiBussinessException {
		
		List<SuperHeroe> heroes = service.getHeroes();
		assertNotNull(heroes);
		assertTrue(heroes.size()>0);
	}
	
	@Test
	void getHeroesByNameTest() throws ApiBussinessException {
		
		List<SuperHeroe> heroes = service.getHeroesByName("man");
		assertNotNull(heroes);
		assertTrue(heroes.size()>0);
	}
	
	@Test
	void getHeroesByIdTest() throws ApiBussinessException {
		
		SuperHeroe heroe = service.getHeroeById(1L);
		assertNotNull(heroe);
		assertEquals("Superman",heroe.getName());
	}
	
	@Test
    void addHeroeTest() {
        SuperHeroeRequest heroe = new SuperHeroeRequest();
        heroe.setName("Black Adam");
        heroe.setAlterEgo("Shazam");
        heroe.setPublisher("DC Comics");
        heroe.setFirstAppearance("1980");
        
        
        SuperHeroe createdHeroe = service.addHeroe(heroe);
        assertNotNull(createdHeroe);
        assertTrue(createdHeroe.getId()<10);
    }
	
	@Test
	void deleteHeroeTest() throws ApiBussinessException {
		
		assertEquals("Heroe was eliminated",service.deleteHeroe(3L));
	}
}
