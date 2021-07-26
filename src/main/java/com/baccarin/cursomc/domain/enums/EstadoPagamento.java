package com.baccarin.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1,"Pendente"), QUITADO(2,"Quitado"), CANCELADO(3,"Cancalado");

	private int id;
	private String descricao;
	
	private EstadoPagamento(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		if (id != null) {
			for (EstadoPagamento estado : EstadoPagamento.values()) {
				if (estado.ordinal() == id) {
					return estado;
				}
			}
		}
		throw new IllegalArgumentException("Id do EstadoPagamento inválido %s" + id);

	}
	
}
