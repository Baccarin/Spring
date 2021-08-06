package com.baccarin.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.baccarin.cursomc.services.exceptions.DataIntegrityExcpetion;
import com.baccarin.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourseExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){		
		StandardError error = new StandardError (HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityExcpetion.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityExcpetion ex, HttpServletRequest request){		
		StandardError error = new StandardError (HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> valitadion(MethodArgumentNotValidException ex, HttpServletRequest request){		
		ValidationError error = new ValidationError (HttpStatus.BAD_REQUEST.value(), "Erro de validacao" , System.currentTimeMillis());
		ex.getFieldErrors().forEach(e -> error.addError(e.getField(), e.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}	
}
