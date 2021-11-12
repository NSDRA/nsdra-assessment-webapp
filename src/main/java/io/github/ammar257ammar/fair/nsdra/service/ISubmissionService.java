package io.github.ammar257ammar.fair.nsdra.service;

import java.util.List;

import io.github.ammar257ammar.fair.nsdra.domain.Submission;
import io.github.ammar257ammar.fair.nsdra.dto.SubmissionDto;


public interface ISubmissionService {
	
	List<Submission> findAll();

    Submission findOneByUuid(String uuid);

    boolean createSubmission(SubmissionDto submission);
}
