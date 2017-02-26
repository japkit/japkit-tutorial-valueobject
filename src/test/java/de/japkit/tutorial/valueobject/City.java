package de.japkit.tutorial.valueobject;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City {
	@Id
	private Long id;
	
	private String name;
	
	public City(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
