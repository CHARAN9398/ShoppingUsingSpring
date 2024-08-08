
package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepoLayer extends JpaRepository<products, Integer> 
{
	List<products> findByCategoryId(Integer categoryId);
	
	List<products> findByNameContainsIgnoreCase(String name);

	static int GroupBycategoryid(int id) 
	{
		return id;
	}

	Optional<products> findByName(String name);
}
