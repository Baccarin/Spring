package com.baccarin.cursomc.domain;

import javax.persistence.Entity;

import com.baccarin.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numeroParcelas;

	public PagamentoComCartao() {

	}

	public PagamentoComCartao(EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

}
