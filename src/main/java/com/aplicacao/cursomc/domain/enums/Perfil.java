package com.aplicacao.cursomc.domain.enums;

public enum Perfil {

ADMIN(1,"ROLE_ADMIN"),
CLIENT(2,"ROLE_CLIENT");

	private int cod;
	private String descricao;

	private Perfil(int cod, String descricao){
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Perfil estadoPagamento : Perfil.values()) {
			if(cod.equals(estadoPagamento.getCod())) {
				return estadoPagamento;
			}
		}
		throw new IllegalArgumentException();
	}
}
