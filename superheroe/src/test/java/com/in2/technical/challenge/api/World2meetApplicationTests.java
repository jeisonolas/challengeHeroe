package com.in2.technical.challenge.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class World2meetApplicationTests {

	@Test
	void contextLoads() {
		
		String load = "load";
		assertEquals("load", load);
	}

}
