package com.heavenhr.javacodingchallenge.service;

import com.heavenhr.javacodingchallenge.model.JobApplication;
import com.heavenhr.javacodingchallenge.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {
    private JobApplicationRepository applicationRepository;

    public JobApplicationService(final JobApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }

    public void createJobApplication(final JobApplication jobApplication){
        applicationRepository.save(jobApplication);
    }
}
