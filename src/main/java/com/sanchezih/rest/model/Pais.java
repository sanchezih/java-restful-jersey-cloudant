package com.sanchezih.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Paises")
public class Pais {

	int id;
	String nombre;
	long poblacion;

	public Pais() {
		super();
	}

	public Pais(int i, String nombre, long poblacion) {
		super();
		this.id = i;
		this.nombre = nombre;
		this.poblacion = poblacion;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement
	public long getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(long poblacion) {
		this.poblacion = poblacion;
	}
}
