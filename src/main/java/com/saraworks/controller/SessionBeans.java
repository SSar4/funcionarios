package com.saraworks.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.saraworks.model.Pessoa;
import com.saraworks.service.PessoaService;
import com.saraworks.util.Message;

@Named
@SessionScoped
public class SessionBeans implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean isLogado = false;

	@Inject
	private PessoaService pessoas;

	private Pessoa pessoa;

	@Inject
	private Message message;

	@PostConstruct
	public void init() {
		pessoa = new Pessoa();
	}

	public String login() {

		try {
			pessoa = pessoas.login(pessoa);
			if (pessoa.getId() > -1L) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pessoa", pessoa.getId());
				this.message.addMessage("Bem vindo " + pessoa.getNome() + "!");
				this.pessoa = new Pessoa();
				System.out.println("is logado " + isLogado);
				this.isLogado = true;
				System.out.println("is logado iii " + isLogado);
				return "faces/home?faces-redirect=true";
			}
		} catch (Exception e) {
			this.message.addMessage("Erro: verifique os dados e tente novamente ");
			return "faces/pessoa?faces-redirect=true";
		}
		this.message.addMessage("Erro: verifique os dados e tente novamente ");

		return "faces/pessoa?faces-redirect=true";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public boolean isIsLogado() {
		return isLogado;
	}

	public Pessoa getUser() {
		return pessoa;
	}

	public void setUser(Pessoa user) {
		this.pessoa = user;
	}

}