package com.example.demo;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class category {
	@Id
	private int id;
	private String name;
	private String Description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		this.Description = description;
	}
	
	@Override
	public String toString()
	{
	   return name;
     }
	@Override
	public int hashCode() {
		return Objects.hash(name, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		category other = (category) obj;
		return Objects.equals(name, other.name) && id == other.id;
	}

	}
	
