package com.in2.technical.challenge.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in2.technical.challenge.domain.entities.SuperHeroe;

public interface ISuperHeroeRepository extends JpaRepository<SuperHeroe, Long> {

	List<SuperHeroe> findByNameContaining(String name);
}
