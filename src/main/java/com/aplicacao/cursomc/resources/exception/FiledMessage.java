package com.aplicacao.cursomc.resources.exception;

import java.io.Serializable;

public class FiledMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	
	public FiledMessage() {
	}
	
	public FiledMessage(String feldName, String message) {
		super();
		this.fieldName = feldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	
}
