package com.aplicacao.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Classe de associação das classes Pedidos e Produto

@Entity
public class ItemPedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPk itemPedido = new ItemPedidoPk();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido ,Produto produto,Double desconto, Integer quantidade, Double preco) {
		super();
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	@JsonIgnore
	public Pedido getPedidos() {
		return itemPedido.getPedido();
	}
	
	public Produto getProdutos() {
		return itemPedido.getProduto();
		
	}
	
	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ItemPedidoPk getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedidoPk itemPedido) {
		this.itemPedido = itemPedido;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemPedido == null) ? 0 : itemPedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (itemPedido == null) {
			if (other.itemPedido != null)
				return false;
		} else if (!itemPedido.equals(other.itemPedido))
			return false;
		return true;
	}
	
}
