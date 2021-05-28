package com.webCrawler.Crawler.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetDocDetails {
	Logger logger = LoggerFactory.getLogger(GetDocDetails.class);
	
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>();
    private Document htmlDocument;


    /**
     * It makes an HTTP request, checks the response, and then gathers up all the links on the page.
     * 
     * @param url
     *            - The URL to visit
     * @return whether the crawl was successful or not
     */
    public boolean getPageContent(String url)
    {
        try
        {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            if(connection.response().statusCode() == 200) 
            {
            	logger.debug("\n Received web page at " + url);
            }
            if(!connection.response().contentType().contains("text/html"))
            {
            	logger.info("Response contains other than HTML");
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
           logger.debug("Found (" + linksOnPage.size() + ") links");
            for(Element link : linksOnPage)
            {
                this.links.add(link.absUrl("href"));
            }
            return true;
        }
        catch(IOException e)
        {
            // Error as HTTP request was not successful
        	logger.error("Exception occured and req was not success",e.getMessage());
            return false;
        }
    }


    /**
     * Performs a search operation on the body of on the HTML document that is retrieved.
     * 
     * @param searchString
     *           
     * @return whether searchstring was found or not 
     */
    public boolean searchForWord(String searchString)
    {
        // This method should only be used after a successful crawl.
        if(this.htmlDocument == null)
        {
            logger.error("ERROR as crawl should be done before performing analysis on the document");
            return false;
        }
        logger.debug("Searching for the word " ,searchString);
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchString.toLowerCase());
    }


    public List<String> getPageLinks()
    {
        return this.links;
    }

}
