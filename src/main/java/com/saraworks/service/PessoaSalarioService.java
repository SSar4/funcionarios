package com.saraworks.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.saraworks.model.Pessoa;
import com.saraworks.model.PessoaSalario;
import com.saraworks.repository.PessoaSalarios;
import com.saraworks.util.Transacional;

public class PessoaSalarioService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Inject
	private PessoaSalarios servicePs;

	public PessoaSalario porId(Long ps) {
		return servicePs.porId(ps);
	}
	
	@Transacional
	public PessoaSalario inserir(PessoaSalario ps) {
		return servicePs.insert(ps);
	}
	
	public float calcularNovoSalario(float salarioAtual, int porcentagemAumento) {
		float aumento = salarioAtual * (porcentagemAumento / 100);
		float novoSalario = salarioAtual + aumento;

		return novoSalario;
	}

	public boolean calcularSalario(Pessoa p, int porcentagem) {
		if (p != null && p.getSalario() != null) {
			p.getSalario().setSalario(calcularNovoSalario(p.getSalario().getSalario(), porcentagem));
			servicePs.insert(p.getSalario());
		}
		return false;
	}

}
