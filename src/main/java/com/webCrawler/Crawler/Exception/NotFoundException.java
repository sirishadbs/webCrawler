package com.webCrawler.Crawler.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 8391922074638467944L;

public NotFoundException(String exception) {
	super(exception);
}
}
