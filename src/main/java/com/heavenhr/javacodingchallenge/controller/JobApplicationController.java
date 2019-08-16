package com.heavenhr.javacodingchallenge.controller;

import com.heavenhr.javacodingchallenge.model.JobApplication;
import com.heavenhr.javacodingchallenge.service.JobApplicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobApplicationController {
    private static final Logger LOGGER = LogManager.getLogger();

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(final JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping(value = "/application", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createJobApplication(@RequestBody final JobApplication jobApplication, final BindingResult bindingResult) {
        jobApplicationService.createJobApplication(jobApplication);
    }


}
