package com.saraworks.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_salario")
public class PessoaSalario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_salario")
	private Long idSalario;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	@Column(name = "salario")
	private Float salario;

	@Override
	public int hashCode() {
		return Objects.hash(idSalario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaSalario other = (PessoaSalario) obj;
		return Objects.equals(idSalario, other.idSalario);
	}

	public Long getId() {
		return idSalario;
	}

	public void setId(Long id) {
		this.idSalario = id;
	}

	public Long getIdSalario() {
		return idSalario;
	}

	public void setIdSalario(Long idSalario) {
		this.idSalario = idSalario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Float getSalario() {
		return salario;
	}

	public void setSalario(Float salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "PessoaSalario [idSalario=" + idSalario + ", pessoa=" + pessoa + ", salario=" + salario + "]";
	}

}
