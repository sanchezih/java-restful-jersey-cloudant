package com.sanchezih.rest.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sanchezih.rest.entities.Pais;

public class PaisService {
	static HashMap<Integer, Pais> paisIdMap = getPaisIdMap();

	public PaisService() {
		super();

		if (paisIdMap == null) {
			paisIdMap = new HashMap<Integer, Pais>();
			// Creating some object of countries while initializing
			Pais paisArgentina = new Pais(1, "Argentina", 10000);
			Pais paisUruguay = new Pais(4, "Uruguay", 20000);
			Pais paisChile = new Pais(3, "Chile", 8000);
			Pais paisBolivia = new Pais(2, "Bolivia", 7000);

			paisIdMap.put(1, paisArgentina);
			paisIdMap.put(4, paisUruguay);
			paisIdMap.put(3, paisChile);
			paisIdMap.put(2, paisBolivia);
		}
	}

	public List<Pais> getAllCountries() {
		List<Pais> countries = new ArrayList<Pais>(paisIdMap.values());
		return countries;
	}

	public Pais getCountry(int id) {
		Pais country = paisIdMap.get(id);
		return country;
	}

	public Pais addCountry(Pais country) {
		country.setId(paisIdMap.size() + 1);
		paisIdMap.put(country.getId(), country);
		return country;
	}

	public Pais updateCountry(Pais country) {
		if (country.getId() <= 0)
			return null;
		paisIdMap.put(country.getId(), country);
		return country;

	}

	public void deleteCountry(int id) {
		paisIdMap.remove(id);
	}

	public static HashMap<Integer, Pais> getPaisIdMap() {
		return paisIdMap;
	}
}
