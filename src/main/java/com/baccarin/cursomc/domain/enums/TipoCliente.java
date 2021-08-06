package com.baccarin.cursomc.domain.enums;

public enum TipoCliente {

	// nao gosto de fazer assim, mas o curso pede assim
	PESSOA_FISICA(1, "Pessoa Física"), PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private int id;
	private String descricao;
	
	private TipoCliente(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public static TipoCliente toEnum(Integer id) {
		if (id != null) {
			for (TipoCliente tipo : TipoCliente.values()) {
				if (tipo.ordinal() == id) {
					return tipo;
				}
			}
		}
		return null;
		//throw new IllegalArgumentException("Id do TipoCliente inválido %s" + id);

	}
}
