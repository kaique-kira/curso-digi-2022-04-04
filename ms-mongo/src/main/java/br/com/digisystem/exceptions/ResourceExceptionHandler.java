package br.com.digisystem.exceptions;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> methodValidation(
			MethodArgumentNotValidException e,
			HttpServletRequest request) {
		
		System.out.println("MethodArgumentNotValidException");
		
		//StandardError error = new StandardError();
		ValidationError error = new ValidationError();
		
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Erro de validação");
		error.setMessage("Erro na validação dos dados da operação");
		error.setPath(request.getRequestURI());
		
		for (int i = 0; i < e.getBindingResult().getFieldErrors().size(); i++ ) {
			
			String fieldName = e.getBindingResult().getFieldErrors().get(i).getField();
			String message = e.getBindingResult().getFieldErrors().get(i).getDefaultMessage();
			error.addError(fieldName, message);
		}
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandardError> noSuchElement(
			NoSuchElementException e,
			HttpServletRequest request){
		StandardError error = new StandardError();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setError("Elemento não encontrado");
		error.setMessage("Erro ao buscar o id no banco de dados");
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
	}
	
	@ExceptionHandler(ObjNotFoundException.class)
	public ResponseEntity<StandardError> objNotFound(
			ObjNotFoundException e,
			HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Elemento não encontrado");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
		
		
		
		
	}
}