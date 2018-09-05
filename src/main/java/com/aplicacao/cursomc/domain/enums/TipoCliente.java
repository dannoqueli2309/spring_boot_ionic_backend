package com.aplicacao.cursomc.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1,"Pessoa Fisíca"),
	PESSOJURIDICA(2,"Pessoa Juridica");
	
	private int cod;
	private String descriptions;
	
	private TipoCliente(int cod, String descricao){
		this.cod = cod;
		this.descriptions = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente tipoCliente : TipoCliente.values()) {
			if(cod.equals(tipoCliente.getCod())) {
				return tipoCliente;
			}
		}
		throw new IllegalArgumentException();
	}
}
