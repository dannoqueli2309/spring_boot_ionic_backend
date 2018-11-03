package com.aplicacao.cursomc.services.exceptions;

public class DataIntegrateException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataIntegrateException(String msg) {
		super(msg);
	}
	
	public DataIntegrateException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
