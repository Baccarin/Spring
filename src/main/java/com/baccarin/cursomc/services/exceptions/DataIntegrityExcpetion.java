package com.baccarin.cursomc.services.exceptions;

public class DataIntegrityExcpetion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityExcpetion (String msg) {
		super(msg);
	}

	public DataIntegrityExcpetion (String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
