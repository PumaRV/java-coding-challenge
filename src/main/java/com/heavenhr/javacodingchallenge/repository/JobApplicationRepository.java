package com.heavenhr.javacodingchallenge.repository;

import com.heavenhr.javacodingchallenge.model.JobApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobApplicationRepository extends CrudRepository<JobApplication, JobApplicationPrimaryKey> {

    List<JobApplication> findAllByJobTitle(final String jobTitle);

    JobApplication findByJobTitleAndCandidateEmail(final String jobTitle, final String candidateEmail);
}
