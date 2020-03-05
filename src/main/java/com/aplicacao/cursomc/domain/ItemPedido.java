package com.aplicacao.cursomc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Classe de associação das classes Pedidos e Produto

@Entity
public class ItemPedido implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @EmbeddedId
  private ItemPedidoPk id = new ItemPedidoPk();

  private Double desconto;
  private Integer quantidade;
  private Double preco;
  private BigDecimal subTotal;

  public ItemPedido() {
  }

  public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
    super();
    id.setPedido(pedido);
    id.setProduto(produto);
    this.desconto = desconto;
    this.quantidade = quantidade;
    this.preco = preco;
  }

  @JsonIgnore
  public Pedido getPedidos() {
    return id.getPedido();
  }

  public void setPedidos(Pedido pedido) {
    id.setPedido(pedido);
  }

  public Produto getProdutos() {
    return id.getProduto();
  }

  public void setProduto(Produto produto) {
    id.setProduto(produto);
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

  public ItemPedidoPk getId() {
    return id;
  }

  public void setId(ItemPedidoPk id) {
    this.id = id;
  }

  public BigDecimal getSubTotal() {
    return this.subTotal = BigDecimal.valueOf((this.preco - this.desconto) * this.quantidade);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ItemPedido other = (ItemPedido) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

}
