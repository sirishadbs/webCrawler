package com.webCrawler.Crawler.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webCrawler.Crawler.Exception.NotFoundException;
import com.webCrawler.Crawler.model.SearchInput;

@Service
public class SearchService {
	Logger logger = LoggerFactory.getLogger(SearchService.class);
	private static final int MAX_PAGES_TO_SEARCH = 10;
    private Set<String> urlPagesVisited = new HashSet<String>();
    private List<String> urlPagesToVisit = new LinkedList<String>();
    @Autowired
    GetDocDetails details;

	public List<String> getResult(SearchInput input) {
		
		String searchString = input.getSearchString();
		List<String> urlList = input.getUrls();
		List<String> urlResultList = new ArrayList<>();
		for(String url : urlList) {
			System.out.println("inside for loop");
			System.out.println(this.urlPagesVisited.size());
			while(this.urlPagesVisited.size() < MAX_PAGES_TO_SEARCH)
		      {
		          String currentUrl;
		          if(this.urlPagesToVisit.isEmpty())
		          {
		              currentUrl = url;
		              this.urlPagesVisited.add(url);
		          }
		          else
		          {
		              currentUrl = this.nextUrl();
		          }
		          details.getPageContent(currentUrl); 
		          boolean success = details.searchForWord(searchString);
		          if(success)
		          {
		        	  urlResultList.add(currentUrl);
		              System.out.println(String.format("**Success** Word %s found at %s", searchString, currentUrl));
		              break;
		          }
		          this.urlPagesToVisit.addAll(details.getPageLinks());
		      }
		      
		      urlResultList.stream().forEach(s-> System.out.println(s));
		      if(urlResultList.isEmpty()) {
		    	  throw new NotFoundException("Search String not Found"+searchString);
		      }
		
		}
		return urlResultList;
	}
	 private String nextUrl()
	  {
	      String nextUrl;
	      do
	      {
	          nextUrl = this.urlPagesToVisit.remove(0);
	      } while(this.urlPagesVisited.contains(nextUrl));
	      this.urlPagesVisited.add(nextUrl);
	      return nextUrl;
	  }

}
