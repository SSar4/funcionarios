package com.saraworks.model;

public enum EnumCargo {
	GERENTE("Gerente"),
	DESENVOLVEDOR("Dev"),
	DP("DP");
	
	private String descricao;

	EnumCargo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
