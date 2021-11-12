package io.github.ammar257ammar.fair.nsdra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.ammar257ammar.fair.nsdra.domain.MaturityIndicatorAssessment;
import io.github.ammar257ammar.fair.nsdra.domain.Submission;
import io.github.ammar257ammar.fair.nsdra.dto.MaturityIndicatorAssessmentDto;
import io.github.ammar257ammar.fair.nsdra.dto.SubmissionDto;
import io.github.ammar257ammar.fair.nsdra.repository.SubmissionRepository;

@Service
public class SubmissionService implements ISubmissionService {

	@Autowired
    private SubmissionRepository submissionRepository;


	@Override
    public List<Submission> findAll() {
        return (List<Submission>) submissionRepository.findAll();
    }
	
	@Override
    public Submission findOneByUuid(String uuid) {
        return (Submission) submissionRepository.findOneByUuid(uuid);
    }
	
	@Override
    public boolean createSubmission(SubmissionDto submissionDto) {
        
        try {
          List<MaturityIndicatorAssessmentDto> miReportedListDto = submissionDto.getMiList();
          
          List<MaturityIndicatorAssessment> miReportedList = new ArrayList<MaturityIndicatorAssessment>(miReportedListDto.size());
                    
          for(MaturityIndicatorAssessmentDto mi: miReportedListDto) {
            
            MaturityIndicatorAssessment miTemp = new MaturityIndicatorAssessment();
            
            miTemp.setComment(mi.getComment());
            miTemp.setListId(mi.getListId());
            miTemp.setListRefernceUrl(mi.getListRefernceUrl());
            miTemp.setListTitle(mi.getListTitle());
            miTemp.setMiId(mi.getMiId());
            miTemp.setStatus(mi.getStatus());
            miTemp.setTitle(mi.getTitle());
            miTemp.setUrl(mi.getUrl());
            miTemp.setVariable(mi.getVariable());
            
            miReportedList.add(miTemp);
          }
          
          Submission submission = new Submission();
          submission.setStatus(submissionDto.getStatus());
          submission.setSubmissionTimestamp(submissionDto.getSubmissionTimestamp());
          submission.setType(submissionDto.getType());
          submission.setUrl(submissionDto.getUrl());
          submission.setUuid(submissionDto.getUuid());
          submission.setMiList(miReportedList);
          
          for(MaturityIndicatorAssessment mi: miReportedList) {
            mi.setSubmission(submission);
          }
          
          submissionRepository.save(submission);
          
          return true;
        }catch(Exception ex) {
            return false;           
        }
    }

}
