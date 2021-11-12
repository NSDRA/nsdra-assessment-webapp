package io.github.ammar257ammar.fair.nsdra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.ammar257ammar.fair.nsdra.domain.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String>{
	 
	Submission findOneByUuid(String uuid);

}
