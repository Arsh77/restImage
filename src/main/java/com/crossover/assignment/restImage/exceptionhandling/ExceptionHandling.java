package com.crossover.assignment.restImage.exceptionhandling;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.amazonaws.AmazonServiceException;
import com.crossover.assignment.restImage.exception.ImproperRequestException;
import com.crossover.assignment.restImage.message.ErrorMessage;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { ImproperRequestException.class})
	public ResponseEntity<Object> handleImproperRequestException(final ImproperRequestException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final ErrorMessage errMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<Object>(errMessage, errMessage.getStatus());
    }
	
	@ExceptionHandler(value = { FileNotFoundException.class})
	public ResponseEntity<Object> handleFileNotFoundException(final FileNotFoundException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final ErrorMessage errMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<Object>(errMessage, errMessage.getStatus());
    }
	
	@ExceptionHandler(value = { IOException.class})
	public ResponseEntity<Object> handleIOException(final IOException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final ErrorMessage errMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<Object>(errMessage, errMessage.getStatus());
    }
	
	@ExceptionHandler(value = { AmazonServiceException.class})
	public ResponseEntity<Object> handleAmazonServiceException(final AmazonServiceException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final ErrorMessage errMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<Object>(errMessage, errMessage.getStatus());
    }

}
