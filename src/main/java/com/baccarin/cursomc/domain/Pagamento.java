package com.baccarin.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.baccarin.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private Integer estadoPagamento;

	@JoinColumn(name = "pedido_id")
	@OneToOne
	@MapsId
	@JsonIgnore
	private Pedido pedido;

	public Pagamento() {

	}

	public Pagamento(EstadoPagamento estado, Pedido pedido) {
		super();
		this.estadoPagamento = estado.getId();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estadoPagamento);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estadoPagamento = estado.getId();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}

}
