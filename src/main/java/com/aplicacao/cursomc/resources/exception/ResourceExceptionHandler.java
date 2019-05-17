package com.aplicacao.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aplicacao.cursomc.services.exceptions.DataIntegrateException;
import com.aplicacao.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice // classe que entercepta as exceptions 
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objectNotFoundException, HttpServletRequest request){
		StandardError errorStandard = new StandardError(HttpStatus.NOT_FOUND.value(), objectNotFoundException.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorStandard);
	}
	
	@ExceptionHandler(DataIntegrateException.class)
	public ResponseEntity<StandardError> dataIntegrate(DataIntegrateException dataIntegrateException, HttpServletRequest request){
		StandardError errorDataIntegrateException = new StandardError(HttpStatus.BAD_REQUEST.value(), dataIntegrateException.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDataIntegrateException);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request){
		ValidationError errorMethodArgumentNotValidException = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		
		for(FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			errorMethodArgumentNotValidException.addFiledMessageError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMethodArgumentNotValidException);
	}
	
}