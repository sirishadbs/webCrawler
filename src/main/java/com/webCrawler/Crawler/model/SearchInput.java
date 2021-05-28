package com.webCrawler.Crawler.model;

import java.util.List;

import org.springframework.lang.NonNull;

public class SearchInput {
	@NonNull
	private List<String> urls;
	@NonNull
	private String searchString;

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

}
