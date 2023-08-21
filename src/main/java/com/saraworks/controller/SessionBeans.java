package com.saraworks.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

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
			System.out.print(pessoa);
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

	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		if (session != null) {
			// Remover os atributos de sessão específicos que você deseja limpar
			session.removeAttribute("pessoa"); // Substitua "usuarioLogado" pelo nome do atributo

			session.invalidate();
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		// Redirecionar para a página de login (você pode ajustar o URL conforme
		// necessário)
		return "/pessoa.xhtml?faces-redirect=true";
	}

	public boolean isLogado() {
		return isLogado;
	}

	public void setLogado(boolean isLogado) {
		this.isLogado = isLogado;
	}

	public PessoaService getPessoas() {
		return pessoas;
	}

	public void setPessoas(PessoaService pessoas) {
		this.pessoas = pessoas;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
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