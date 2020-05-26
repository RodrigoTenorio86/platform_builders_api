package br.com.PlatformBuilders.PlatformBuildersApi.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.PlatformBuilders.PlatformBuildersApi.erros.ResourceNotFoundDetails;
import br.com.PlatformBuilders.PlatformBuildersApi.erros.ResourceNotFoundException;
import br.com.PlatformBuilders.PlatformBuildersApi.erros.ValidationErrorDetails;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	@Override
	public ResponseEntity<Object>  handleMethodArgumentNotValid(MethodArgumentNotValidException rfnException, 
																HttpHeaders headers, 
																HttpStatus status, 
																WebRequest request) {		
		
		List<FieldError> fieldErrors = rfnException.getBindingResult().getFieldErrors();
		
		String field = fieldErrors.parallelStream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessages = fieldErrors.parallelStream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		
		
		ValidationErrorDetails builds = ValidationErrorDetails.Builder
				                                              .newBuilder()
				                                              .timestamp(LocalDateTime.now())
				                                              .detail("Field Validation Error. ")
				                                              .title( "Field Validation Error. ")
				                                              .developerMessage(rfnException.getClass().getName())
				                                              .status(status.value())
				                                              .field(field)
				                                              .fieldMessage(fieldMessages)
				                                              .build();


	    return new ResponseEntity<>(builds, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException){
		ResourceNotFoundDetails build =  ResourceNotFoundDetails.Builder
				                                                .newBuilder()
				                                                .timestamp(LocalDateTime.now())
				                                                .status(HttpStatus.NOT_FOUND.value())
				                                                .title("Resource Not Found.")
				                                                .detail(rnfException.getMessage())
				                                                .developerMessage(rnfException.getClass().getSimpleName())
				                                                .build();
		
		
		return new ResponseEntity<>(build,HttpStatus.NOT_FOUND);
	}
}
