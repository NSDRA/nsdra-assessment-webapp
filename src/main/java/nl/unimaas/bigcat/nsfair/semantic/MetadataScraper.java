package nl.unimaas.bigcat.nsfair.semantic;

import hwu.elixir.scrape.exceptions.FourZeroFourException;
import hwu.elixir.scrape.exceptions.SeleniumException;
import hwu.elixir.scrape.scraper.ScraperCore;
import hwu.elixir.scrape.scraper.ScraperUnFilteredCore;

import org.apache.any23.source.DocumentSource;
import org.apache.any23.source.StringDocumentSource;
import org.eclipse.rdf4j.model.Model;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetadataScraper extends ScraperUnFilteredCore {
	
	private static Logger logger = LoggerFactory.getLogger(ScraperCore.class.getName());

	public Repository scrape(String url, String type) throws Exception{
		
		String html = "";
		url = fixURL(url);
		
		if(type.equals("static")){
		
			try {
				html = getHtml(url);
			} catch (FourZeroFourException e) {
				logger.error("404 error " + e);
				return null;
			}
			
		}else if(type.equals("dynamic")){
			
			try {
				html = getHtmlViaSelenium(url);
			} catch (SeleniumException e) {
				try {
					html = getHtmlViaSelenium(url);
				} catch (SeleniumException e2) {
					logger.error("Selenium Exception" + e2);
					return null;
				}
			}
		}
		
		if(html == null || html.trim().contentEquals("")){
			return null;
		}
		
		DocumentSource source = new StringDocumentSource(html, url);
		String triples = getTriplesInNTriples(source);
		
		if (triples == null) {
			logger.error("Cannot obtain triples from " + url);
			return null;
		}

		Model model = processTriplesLeaveBlankNodes(triples);
		if (model == null) {
			logger.error("Cannot build model for " + url);
			return null;
		}
		
		Repository repo = new SailRepository(new MemoryStore());

		repo.initialize();
		
		try(RepositoryConnection conn = repo.getConnection()) {
		    conn.add(model);
		}
		
		return repo;
	}
}
