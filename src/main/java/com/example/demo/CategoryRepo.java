package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<category, Integer>
{

	Optional<category> findByName(String name);
	
}
