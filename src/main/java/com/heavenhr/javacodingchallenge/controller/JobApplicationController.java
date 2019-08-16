package com.heavenhr.javacodingchallenge.controller;

import com.heavenhr.javacodingchallenge.model.JobApplication;
import com.heavenhr.javacodingchallenge.service.JobApplicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobApplicationController {
    private static final Logger LOGGER = LogManager.getLogger();

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(final JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping(value = "/applications/{jobTitle}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createJobApplication(@PathVariable(value = "jobTitle") final String jobTitle, @RequestBody final JobApplication jobApplication, final BindingResult bindingResult) {
        jobApplication.setJobTitle(jobTitle);
        jobApplicationService.createJobApplication(jobApplication);
    }

    @GetMapping(value = "/applications/{jobTitle}/{email}")
    @ResponseStatus(HttpStatus.OK)
    public JobApplication getAllOffers(@PathVariable(value = "jobTitle") final String jobTitle, @PathVariable(value = "email") final String email) {
        return jobApplicationService.getJobApplicationByPrimaryKey(jobTitle, email);
    }

    @GetMapping(value = "/applications/{jobTitle}")
    @ResponseStatus(HttpStatus.OK)
    public List<JobApplication> getAllOffers(@PathVariable(value = "jobTitle") final String jobTitle) {
        return jobApplicationService.getJobApplicationsByJobTitle(jobTitle);
    }

}
