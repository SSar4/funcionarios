package com.saraworks.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.saraworks.model.EnumCargo;
import com.saraworks.model.Pessoa;
import com.saraworks.model.PessoaSalario;
import com.saraworks.service.CargoService;
import com.saraworks.service.PessoaSalarioService;
import com.saraworks.service.PessoaService;
import com.saraworks.util.Transacional;

@Named
@SessionScoped
public class PessoaBeans implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaService pessoas;

	@Inject
	private CargoService cargosService;
	
	@Inject
	private PessoaSalarioService ps;

	
	private Pessoa pessoa;
	private List<Pessoa> listaPessoas;
	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	private int valorSelecionado;

	@PostConstruct
	public void init() {
		pessoa = new Pessoa();
		//listaPessoas = getListarTodas();
	}

	
	public String cadastra() {

		pessoa.setCargo(cargosService.porId((long) valorSelecionado));
		pessoa = pessoas.inserir(pessoa);
		
			pessoa.setSalario(pessoa.getSalario());
			listaPessoas.add(pessoa);
			valorSelecionado = 0;
			pessoa = new Pessoa();
		
		pessoa = new Pessoa();
	
		return "home.xhtml";
	}

	public String excluir(Pessoa p) {
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

	public String editar(Pessoa p) {
		cargosService.porId(p.getId());
		return null;
	}

	public EnumCargo[] getTiposCargos() {
		return EnumCargo.values();
	}

	public String preencherFormulario(int id) {
		System.out.print(id);
		pessoa = pessoas.porId((long) id);
		valorSelecionado = pessoa.getCargo().getIdCargo().intValue();
		return "form_pessoa.xhtml";
	}

	public int getValorSelecionado() {
		return valorSelecionado;
	}

	public void setValorSelecionado(int valorSelecionado) {
		this.valorSelecionado = valorSelecionado;
	}
	
	public String iniciarForm() {
		return "form_pessoa.xhtml";
	}
	
	public String paginaCalcularSalario(Pessoa p) {
		pessoa = p;
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
    	pessoa.getSalario().setSalario(resultado);;
        ps.inserir(pessoa.getSalario());
        resultado = 0;
        porcentagemAumento = 0;
        return "home.xhtml";
    }

}