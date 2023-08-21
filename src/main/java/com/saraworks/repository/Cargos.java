package com.saraworks.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.saraworks.model.Cargo;

public class Cargos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Cargos() {
	}
	
	public Cargo porId(Long id) {
		return manager.find(Cargo.class, id);
	}

	public void remover(Cargo cargo) {
		manager.remove(porId(cargo.getIdCargo()));
	}

}
