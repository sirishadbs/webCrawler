package com.webCrawler.Crawler.Exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.webCrawler.Crawler.model.ErrorResponse;
@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustExcepHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundExcep(Exception e, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(e.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse("Not Found", details);
		return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
	}
}
