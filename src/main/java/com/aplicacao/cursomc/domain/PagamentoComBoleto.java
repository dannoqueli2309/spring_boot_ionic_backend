package com.aplicacao.cursomc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.aplicacao.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoComBoleto extends Pagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date datavencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
	}
	
	public PagamentoComBoleto(Integer id,EstadoPagamento estado,Pedido pedido, Date datavencimento, Date dataPagamento) {
		super(id,estado,pedido);
		this.datavencimento = datavencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDatavencimento() {
		return datavencimento;
	}

	public void setDatavencimento(Date datavencimento) {
		this.datavencimento = datavencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}
