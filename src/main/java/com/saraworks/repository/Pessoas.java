package com.saraworks.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.saraworks.model.Pessoa;

public class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;


	public Pessoas() {
	}

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}

	public List<Pessoa> pesquisar(String nome) {
		TypedQuery<Pessoa> query = manager.createQuery("from pessoa where nome like :nome", Pessoa.class);
		query.setParameter("nome", nome + "%");
		return query.getResultList();
	}

	public Pessoa guardar(Pessoa pessoa) {
		Pessoa p = manager.merge(pessoa);
		return p;
	}

	public void remover(Pessoa pessoa) {
		manager.remove(porId(pessoa.getId()));
	}

	public Pessoa login(Pessoa pessoa) {
		TypedQuery<Pessoa> query = manager.createQuery("from Pessoa where email = :email and senha = :senha",
				Pessoa.class);
		query.setParameter("email", pessoa.getEmail());
		query.setParameter("senha", pessoa.getSenha());
		query.setMaxResults(1);
		return query.getSingleResult();
	}

	public List<Pessoa> listar() {
		// SELECT DISTINCT e FROM Pessoa e LEFT  JOIN e.cargo c =
		// c.id_cargo
		TypedQuery<Pessoa> query = manager.createQuery("SELECT DISTINCT e FROM Pessoa e LEFT  JOIN e.cargo c LEFT JOIN e.salario s",
				Pessoa.class);
		List<Pessoa> pessoas = query.getResultList();

		for (Pessoa pessoa : pessoas) {
			pessoa.setSenha(null);
		}

		return pessoas;
	}

	public Pessoa atualizar(Pessoa p) {
			String jpql = "from Pessoa where id :id";
			
			TypedQuery<Pessoa> query = manager
					.createQuery(jpql, Pessoa.class);
			
			query.setParameter("nomeFantasia", p.getId());
			query.setMaxResults(1);
			return (Pessoa) query.getResultList();
	}

}
