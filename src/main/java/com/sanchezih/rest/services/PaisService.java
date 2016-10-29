package com.sanchezih.rest.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.sanchezih.rest.exceptions.DataNotFoundException;
import com.sanchezih.rest.model.Pais;

public class PaisService {
	static HashMap<Integer, Pais> paises = getPaisIdMap();

	public PaisService() {
		super();

		if (paises == null) {
			paises = new HashMap<Integer, Pais>();

			// Se crean algunos paises en la inicializacion
			paises.put(1, new Pais(1, "Argentina", 10000));
			paises.put(4, new Pais(4, "Uruguay", 20000));
			paises.put(3, new Pais(3, "Chile", 8000));
			paises.put(2, new Pais(2, "Bolivia", 7000));
		}
	}

	public List<Pais> getAllPaises() {
		List<Pais> countries = new ArrayList<Pais>(paises.values());
		return countries;
	}

	public Pais getCountry(int id) {
		Pais pais = paises.get(id);
		if (pais == null) {
			throw new DataNotFoundException("El pais con id " + id + " no fue encontrado");
		}
		return pais;
	}

	public Pais addPais(Pais pais) {
		pais.setId(paises.size() + 1);
		paises.put(pais.getId(), pais);
		return pais;
	}

	public Pais updatePais(Pais pais) {
		if (pais.getId() <= 0)
			return null;
		paises.put(pais.getId(), pais);
		return pais;

	}

	public void deleteCountry(int id) {
		paises.remove(id);
	}

	public static HashMap<Integer, Pais> getPaisIdMap() {
		return paises;
	}
}
