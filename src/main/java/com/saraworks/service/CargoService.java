package com.saraworks.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.saraworks.model.Cargo;
import com.saraworks.repository.Cargos;
import com.saraworks.util.Transacional;

public class CargoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Cargos cargos;

	@Transacional
	public void excluir(Cargo c) {
		cargos.remover(c);
	}

	public Cargo porId(Long id) {
		return cargos.porId(id);
	}

}