package com.saraworks.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.saraworks.model.Pessoa;
import com.saraworks.model.PessoaSalario;
import com.saraworks.repository.PessoaSalarios;
import com.saraworks.repository.Pessoas;
import com.saraworks.util.Transacional;

public class PessoaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Pessoas pessoas;

	@Inject
	private PessoaSalarios ps;

	public Pessoa inserir(Pessoa p) {
		if (p.getId() == null) {
			PessoaSalario ps = new PessoaSalario();
			ps.setSalario(p.getCargo().getSalario());
			ps.setPessoa(p);
			p.setSalario(ps);
		} else {
			p.getSalario().setSalario(p.getCargo().getSalario());;
		}
		
		System.out.print(p.getId());

		return pessoas.guardar(p);
	}

	@Transacional
	public void excluir(Pessoa p) {
		pessoas.remover(p);
	}

	public Pessoa porId(Long id) {
		return pessoas.porId(id);
	}

	public Pessoa login(Pessoa p) {
		return pessoas.login(p);
	}

	public List<Pessoa> listar() {
		return pessoas.listar();
	}

	public PessoaSalarios getPs() {
		return ps;
	}

	public void setPs(PessoaSalarios ps) {
		this.ps = ps;
	}

}
