package nl.unimaas.bigcat.nsfair.web;

import java.net.URI;

import org.eclipse.rdf4j.repository.Repository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import nl.unimaas.bigcat.nsfair.domain.MaturityIndicator;
import nl.unimaas.bigcat.nsfair.domain.Submission;
import nl.unimaas.bigcat.nsfair.semantic.FairAssessor;
import nl.unimaas.bigcat.nsfair.semantic.MetadataScraper;

@Controller
public class MainController {

	@GetMapping("/")
    public ModelAndView getIndex() {

        return new ModelAndView("index");
    }
	
	@GetMapping("/generator")
    public ModelAndView getGenerator() {

        return new ModelAndView("generator");
    }
	
	@RequestMapping(value = "/assess", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Submission> assessResource(@RequestBody Submission submission)
    {
		MetadataScraper scraper = new MetadataScraper();
		
		Repository repo = null;
		try {
			System.out.println(submission.getType());
			repo = scraper.scrape(submission.getUrl(), submission.getType());
	
			submission.setStatus("Assessed");

		} catch (Exception e) {
			submission.setStatus("Not Assessed");
		}		

		if(submission.getStatus().equals("Assessed")){
		
			MaturityIndicator mi_R1_3_NT_PCHEM = FairAssessor.assess_MI_R1_3_NT_PCHEM(repo);
			submission.getResults().add(mi_R1_3_NT_PCHEM);
			
			MaturityIndicator mi_R1_3_NT_UNIT = FairAssessor.assess_MI_R1_3_NT_UNIT(repo);
			submission.getResults().add(mi_R1_3_NT_UNIT);
		}
		
		return ResponseEntity
	            .created(URI.create(submission.getUrl()))
	            .body(submission);
    }
}
