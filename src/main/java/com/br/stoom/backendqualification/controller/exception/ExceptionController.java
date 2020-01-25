package com.br.stoom.backendqualification.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController{

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleConstaintViolatoinException(final MethodArgumentNotValidException ex) {

		StringBuilder message = new StringBuilder();
		BindingResult result = ex.getBindingResult();
    
		for (ObjectError error : result.getAllErrors()) {
			message.append(error.getDefaultMessage().concat("\n"));
		}
	 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
	
	@ExceptionHandler(GeocodingException.class)
	public ResponseEntity<?> handleConstaintViolatoinException(final GeocodingException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	
    @ExceptionHandler
    @ResponseBody
    ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.status(500).body("[Erro Inesperado]: " + ex.getMessage());
    }
}