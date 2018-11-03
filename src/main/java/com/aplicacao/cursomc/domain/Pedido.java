package com.aplicacao.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable{

private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
private Pagamento pagamento;

@ManyToOne
@JoinColumn(name = "cliente_id")
private Cliente cliente;

@ManyToOne
@JoinColumn(name = "endereco_de_entrega_id")
private Endereco enderecoDeEntrega;

@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
private Date instanteDate;

@OneToMany(mappedBy = "itemPedido.pedido")
private Set<ItemPedido> items = new HashSet<>();

public Pedido() {
}

public Pedido(Integer id, Date instanteDate,  Cliente cliente, Endereco enderecos) {
	super();
	this.id = id;
	this.instanteDate = instanteDate;
	this.cliente = cliente;
	this.enderecoDeEntrega = enderecos;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Date getInstanteDate() {
	return instanteDate;
}

public void setInstanteDate(Date instanteDate) {
	this.instanteDate = instanteDate;
}

public Pagamento getPagamento() {
	return pagamento;
}

public void setPagamento(Pagamento pagamento) {
	this.pagamento = pagamento;
}

public Cliente getCliente() {
	return cliente;
}

public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}

public Endereco getEnderecoDeEntrega() {
	return enderecoDeEntrega;
}

public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
	this.enderecoDeEntrega = enderecoDeEntrega;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}

public Set<ItemPedido> getItems() {
	return items;
}

public void setItems(Set<ItemPedido> items) {
	this.items = items;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Pedido other = (Pedido) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}



}