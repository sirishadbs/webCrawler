package com.webCrawler.Crawler.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webCrawler.Crawler.model.SearchInput;
import com.webCrawler.Crawler.service.SearchService;

@RestController
public class SearchController {
	Logger logger = LoggerFactory.getLogger(SearchController.class);
	@Autowired
	SearchService searchService;
	
	@PostMapping(path = "/crawler", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<String>> getSearchResult(@Valid @RequestBody SearchInput input){
		logger.debug("inside controller and URL list size is", input.getUrls().size());
		List<String> resultList = searchService.getResult(input);
		return new ResponseEntity<List<String>>(resultList, HttpStatus.OK);
		
	}
	

}
