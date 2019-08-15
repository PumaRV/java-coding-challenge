package com.heavenhr.javacodingchallenge.repository;

import com.heavenhr.javacodingchallenge.model.JobApplication;
import org.springframework.data.repository.CrudRepository;

public interface JobApplicationRepository extends CrudRepository<JobApplication, JobApplicationPrimaryKey> {
}
