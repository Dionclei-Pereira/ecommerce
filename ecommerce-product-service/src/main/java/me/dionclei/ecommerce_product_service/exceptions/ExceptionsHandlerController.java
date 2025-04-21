package me.dionclei.ecommerce_product_service.exceptions;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandlerController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<String> error = e.getAllErrors().stream().map(er -> er.getDefaultMessage()).toList();
		var err = new StandardError(Instant.now(), status.value(), "Validation Exception", error.toString(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
