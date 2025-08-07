package com.vinim.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Endereco enderecoDeEntrega;
	@OneToMany(mappedBy="id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	public Pedido() {
	}

	public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;

		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	
	public  double getValorTotal() {
		
	 return	itens.stream().mapToDouble(item -> item.getSubTotal()).sum();
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
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
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
		SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(this.getId());
		builder.append(", Instante : ");
		builder.append(sdf.format(this.getInstante()));
		builder.append(", Cliente : ");
		builder.append(this.getCliente().getNome());
		builder.append(", Situação do pagamento : ");
		builder.append(this.getPagamento().getEstado().getDescricao());
		builder.append("\nDetalhes:\n");
		for(ItemPedido ip : this.getItens()) {
			builder.append(ip.toString());
		}
		builder.append("Valor Total : ");
		builder.append(nf.format(this.getValorTotal()));
		 
		return builder.toString();
	}
	
	
}
