package com.citadini.navigationsystem.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.citadini.navigationsystem.services.exceptions.CommunicationNotEstablishedException;
import com.citadini.navigationsystem.services.exceptions.LimitQuantityExceededException;
import com.citadini.navigationsystem.services.exceptions.ObjectNotFoundExcpetion;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LimitQuantityExceededException.class)
	public final ResponseEntity<ErrorDetails> handleLimitQuantityExceededException(
			LimitQuantityExceededException excepetion, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), excepetion.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ObjectNotFoundExcpetion.class)
	public final ResponseEntity<ErrorDetails> handleObjectNotFoundExcpetion(ObjectNotFoundExcpetion excepetion,
			WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), excepetion.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CommunicationNotEstablishedException.class)
	public final ResponseEntity<CommunicationNotEstablished> handleCommunicationNotEstablishedException(
			CommunicationNotEstablishedException excepetion, WebRequest request) throws Exception {
		CommunicationNotEstablished communicationNotEstablished = new CommunicationNotEstablished(LocalDateTime.now(),
				excepetion.getErrorCode(), excepetion.getLatitude(), excepetion.getLongitude(),
				excepetion.getMobileStationid(), excepetion.getMessage());

		return new ResponseEntity<CommunicationNotEstablished>(communicationNotEstablished, HttpStatus.BAD_REQUEST);
	}
}
