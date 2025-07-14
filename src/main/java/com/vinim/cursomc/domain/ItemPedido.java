package com.vinim.cursomc.domain;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	private Double desconto;
	private Integer quatidade;
	private Double preco;

	public ItemPedido() {

	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quatidade, Double preco) {

		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quatidade = quatidade;
		this.preco = preco;
	}

	public Pedido getPedido() {
		return id.getPedido();
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuatidade() {
		return quatidade;
	}

	public void setQuatidade(Integer quatidade) {
		this.quatidade = quatidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
