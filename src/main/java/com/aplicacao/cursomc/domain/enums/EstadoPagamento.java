package com.aplicacao.cursomc.domain.enums;

public enum  EstadoPagamento{
PENDENTE(1,"Pendente"),
QUITADO(2,"Quitado"),
CANCELADO(3,"Cancelado");
	private int cod;
	private String descriptions;
	
	private EstadoPagamento (int cod, String descricao){
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
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
			if(cod.equals(estadoPagamento.getCod())) {
				return estadoPagamento;
			}
		}
		throw new IllegalArgumentException();
	}
}
