package com.saraworks.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.saraworks.model.EnumCargo;
import com.saraworks.model.Pessoa;
import com.saraworks.service.CargoService;
import com.saraworks.service.PessoaService;
import com.saraworks.util.Message;

@Named
@SessionScoped
public class PessoaBeans implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaService pessoas;

	@Inject
	private Message message;

	@Inject
	private CargoService cargosService;

	private Pessoa pessoa;
	private List<Pessoa> listaPessoas;

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	private int valorSelecionado;

	@PostConstruct
	public void init() {
		pessoa = new Pessoa();
		listaPessoas = getListaPessoas();
	}

	public String cadastra() {
		if (!checkPermissao()) {
			this.message.addMessage("err: voce nao tem permissao");
			return "home.xhtml";
		}
		pessoa.setCargo(cargosService.porId((long) valorSelecionado));
		pessoa = pessoas.inserir(pessoa);

		pessoa.setSalario(pessoa.getSalario());
		listaPessoas.add(pessoa);
		valorSelecionado = 0;

		pessoa = new Pessoa();

		return "home.xhtml?faces-redirect=true";
	}

	public String excluir(Pessoa p) {
		if (!checkPermissao()) {
			this.message.addMessage("err: voce nao tem permissao");
			return "home.xhtml?faces-redirect=true";
		}
		pessoas.excluir(p);
		return null;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getListaPessoas() {
		listaPessoas = pessoas.listar();
		return listaPessoas;
	}

	public boolean checkPermissao() {
		Long usuario = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pessoa");
		Pessoa isAdmin = pessoas.porId(usuario);

		if (isAdmin.getCargo().getIdCargo() == 1) {
			return true;
		}

		return false;
	}

	public String editar(Pessoa p) {
		pessoa = p;
		return "home.xhtml";
	}

	public EnumCargo[] getTiposCargos() {
		return EnumCargo.values();
	}

	public String preencherFormulario(int id) {
		if(!checkPermissao()) {
			this.message.addMessage("err: voce nao tem permissao");
			return "home.xhtml?faces-redirect=true";
		}
		pessoa = pessoas.porId((long) id);
		valorSelecionado = pessoa.getCargo().getIdCargo().intValue();
		this.message.addMessage("sucesso");
		Pessoa nova = pessoas.porId((long) id);
		pessoa = nova;
		return "form_pessoa.xhtml?faces-redirect=true";

	}

	public int getValorSelecionado() {
		return valorSelecionado;
	}

	public void setValorSelecionado(int valorSelecionado) {
		this.valorSelecionado = valorSelecionado;
	}

	public String iniciarForm() {
		if (!checkPermissao()) {
			this.message.addMessage("err: voce nao tem permissao");
			return "home.xhtml?faces-redirect=true";
		}
		return "form_pessoa.xhtml?faces-redirect=true";
	}

	public String paginaCalcularSalario(Pessoa p) {
		if(!checkPermissao()) {
			this.message.addMessage("err: voce nao tem permissao");
			return "home.xhtml?faces-redirect=true";
		}
		pessoa = p;
		resultado = 0;
		return "salarios.xhtml";
	}

	private int porcentagemAumento;
	private float resultado;

	public int getPorcentagemAumento() {
		return porcentagemAumento;
	}

	public void setPorcentagemAumento(int porcentagemAumento) {
		this.porcentagemAumento = porcentagemAumento;
	}

	public float getResultado() {
		return resultado;
	}

	public void setResultado(float resultado) {
		this.resultado = resultado;
	}

	public void calcularAumento() {
		float salarioAtual = pessoa.getSalario().getSalario();
		float aumento = (float) (salarioAtual * (porcentagemAumento / 100.0));
		float novoSalario = salarioAtual + aumento;

		resultado = novoSalario;

	}

	public String salvarSalario() {

		pessoa.getCargo().setSalario(resultado);
		pessoa = pessoas.inserir(pessoa);

		pessoa.setSalario(pessoa.getSalario());
		listaPessoas.add(pessoa);
		valorSelecionado = 0;

		pessoa.getSalario().setSalario(0F);

		porcentagemAumento = 0;
		return "home.xhtml?faces-redirect=true";
	}

}