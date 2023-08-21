package com.saraworks.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;


import com.saraworks.model.PessoaSalario;


public class PessoaSalarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public PessoaSalarios() {
	}

	public PessoaSalario insert(PessoaSalario ps) {
		PessoaSalario result = manager.merge(ps);
		return result;
	}
	public PessoaSalario porId(Long id) {
		return manager.find(PessoaSalario.class, id);
	}
	
}