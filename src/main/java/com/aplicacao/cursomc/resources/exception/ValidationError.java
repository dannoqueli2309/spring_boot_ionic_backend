package com.aplicacao.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String mensagemErro, Long timeStamp) {
		super(status, mensagemErro, timeStamp);
	}

	public List<FieldMessage> getErrors() {  // o nome do Json é pego por reflexion do metodo Get
		return this.errors;
	}

	public void addFiledMessageError(String filedName, String messagem) {
		errors.add(new FieldMessage(filedName,messagem));
	}
}
