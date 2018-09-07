package com.aplicacao.cursomc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.aplicacao.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Date datavencimento;
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
