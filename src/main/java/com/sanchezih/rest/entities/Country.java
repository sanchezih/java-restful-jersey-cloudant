package com.sanchezih.rest.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Countries")
public class Country {

	int id;
	String countryName;
	long population;

	public Country() {
		super();
	}

	public Country(int i, String countryName, long population) {
		super();
		this.id = i;
		this.countryName = countryName;
		this.population = population;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@XmlElement
	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}
}
