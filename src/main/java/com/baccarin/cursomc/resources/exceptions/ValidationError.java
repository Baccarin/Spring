package com.baccarin.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FieldMessage> listMessageErros = new ArrayList<>();

	public ValidationError(Integer status, String mensagem, Long timeStamp) {
		super(status, mensagem, timeStamp);
	}

	public List<FieldMessage> getErros() {
		return listMessageErros;
	}

	public void setListMessage(List<FieldMessage> listMessage) {
		this.listMessageErros = listMessage;
	}

	public void addError(String fieldName, String message) {
		this.listMessageErros.add(new FieldMessage(fieldName, message));
	}

	public void addError(FieldMessage message) {
		this.listMessageErros.add(message);
	}
}
