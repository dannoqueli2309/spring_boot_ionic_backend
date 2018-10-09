package com.aplicacao.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aplicacao.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice // classe que entercepta as exceptions 
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objectNotFoundException, HttpServletRequest request){
		StandardError errorStandard = new StandardError(HttpStatus.NOT_FOUND.value(), objectNotFoundException.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorStandard);
	}
}