package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class products /*implements Comparable<products>*/ {
    
    @Id
    private int sno;
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "id")
    private category category;
    public int getSno() {
        return sno;
    }
    public void setSno(int sno) {
        this.sno = sno;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public category getCategory() {
        return category;
    }
    public void setCategory(category category) {
        this.category = category;
    }
    
	/*@Override
	public int compareTo(products productObj) 
	{
		return this.name.compareTo(productObj.name);
	}*/
    
}
