package io.github.ammar257ammar.fair.nsdra.web;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.eclipse.rdf4j.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.openjson.JSONObject;

import io.github.ammar257ammar.fair.nsdra.domain.Submission;
import io.github.ammar257ammar.fair.nsdra.dto.MaturityIndicatorAssessmentDto;
import io.github.ammar257ammar.fair.nsdra.dto.SubmissionDto;
import io.github.ammar257ammar.fair.nsdra.semantic.FairAssessor;
import io.github.ammar257ammar.fair.nsdra.semantic.MetadataScraper;
import io.github.ammar257ammar.fair.nsdra.service.SubmissionService;

@Controller
public class MainController {

  @Autowired
  private JSONObject miJsonDescription;
  
  @Autowired
  private SubmissionService submissionService;

  @GetMapping("/")
  public ModelAndView getIndex() {

    ModelAndView mv = new ModelAndView("index");
    mv.addObject("lists", miJsonDescription.toString());

    return mv;
  }
  
  @GetMapping("assessment/{uuid}")
  public ModelAndView getAssessmentReport(@PathVariable(value="uuid") String uuid) {
    
    Submission submission = submissionService.findOneByUuid(uuid);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    String submissionStr = null;

    try {
      submissionStr = objectMapper.writeValueAsString(submission);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }

    ModelAndView mv = new ModelAndView("report");

    mv.addObject("submission", submissionStr);

    return mv;
  }

  @GetMapping("/test.html")
  public ModelAndView getTestPage() {

    ModelAndView mv = new ModelAndView("test");
    return mv;
  }

  @GetMapping("/metadata-generator")
  public void redirectToGenerator(HttpServletResponse httpServletResponse) {
    httpServletResponse.setHeader("Location",
        "https://nsdra.github.io/nsdra-jsonld-metadata-generator-webapp");
    httpServletResponse.setStatus(302);
  }

  @RequestMapping(value = "/assess", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<SubmissionDto> assessResource(
      @RequestBody SubmissionDto submission) {
    
    MetadataScraper scraper = new MetadataScraper();

    Repository repo = null;
    try {
      repo = scraper.scrape(submission.getUrl(), submission.getType());

      submission.setStatus("Assessed");

    } catch (Exception e) {
      submission.setStatus("Not Assessed");
    }

    if (submission.getStatus().equals("Assessed")) {

      List<MaturityIndicatorAssessmentDto> miReportedList = FairAssessor
          .assessSubmission(repo, submission.getMiList());
      submission.setMiList(miReportedList);

      SimpleDateFormat formatter = new SimpleDateFormat(
          "yyyy-MM-dd 'at' HH:mm:ss z");
      Date date = new Date(System.currentTimeMillis());

      submission.setSubmissionTimestamp(formatter.format(date));
      
      String uuidStr = submission.getUrl()+submission.getSubmissionTimestamp()+submission.getStatus();
      
      submission.setUuid(UUID.nameUUIDFromBytes(uuidStr.getBytes()).toString());
      
      if(submission.isPresistResults()) {
        submissionService.createSubmission(submission);        
      }
    }

    return ResponseEntity.created(URI.create(submission.getUrl()))
        .body(submission);
  }
  
  @RequestMapping(value = "/sitemap.xml",method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
  public @ResponseBody ModelAndView  generateSitemap(HttpServletResponse response) throws IOException {
      
      response.setContentType(MediaType.APPLICATION_XML_VALUE); 
      
      return new ModelAndView("sitemap");
  }
  
  @RequestMapping(value = "/robots.txt",method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
  public @ResponseBody ModelAndView  generateRobots(HttpServletResponse response) throws IOException {
      
      response.setContentType(MediaType.TEXT_PLAIN_VALUE); 
      
      return new ModelAndView("robots");
  }
}
