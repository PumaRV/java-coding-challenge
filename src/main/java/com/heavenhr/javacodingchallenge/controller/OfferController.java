package com.heavenhr.javacodingchallenge.controller;

import com.heavenhr.javacodingchallenge.model.JobApplication;
import com.heavenhr.javacodingchallenge.model.Offer;
import com.heavenhr.javacodingchallenge.service.JobApplicationService;
import com.heavenhr.javacodingchallenge.service.OfferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OfferController {
    private static final Logger LOGGER = LogManager.getLogger();

    private final OfferService offerService;
    private final JobApplicationService jobApplicationService;


    public OfferController(final OfferService offerService, final JobApplicationService jobApplicationService){
        this.offerService = offerService;
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping(value = "/offers", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createJobOffer(@RequestBody final Offer offer, final BindingResult bindingResult){
        if (bindingResult.hasErrors() || offer == null) {
            throw new IllegalArgumentException("Error while trying to parse data");
        }
    offerService.createOffer(offer);
    }

    @GetMapping(value = "/offers")
    @ResponseStatus(HttpStatus.OK)
    public List<Offer> getAllOffers(){
        return offerService.getAllOffers();
    }

    @PostMapping(value = "/offers/{jobTitle}/applications", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createJobApplication(@PathVariable(value = "jobTitle") final String jobTitle, @RequestBody final JobApplication jobApplication, final BindingResult bindingResult) {
        jobApplication.setJobTitle(jobTitle);
        jobApplicationService.createJobApplication(jobApplication);
    }

    @GetMapping(value = "/offers/{jobTitle}/applications/{email}")
    @ResponseStatus(HttpStatus.OK)
    public JobApplication getApplicationById(@PathVariable(value = "jobTitle") final String jobTitle, @PathVariable(value = "email") final String email) {
        return jobApplicationService.getJobApplicationByPrimaryKey(jobTitle, email);
    }

    @GetMapping(value = "/offers/{jobTitle}/applications")
    @ResponseStatus(HttpStatus.OK)
    public List<JobApplication> getApplicationsByOffer(@PathVariable(value = "jobTitle") final String jobTitle) {
        return jobApplicationService.getJobApplicationsByJobTitle(jobTitle);
    }

    @GetMapping(value = "/offers/{jobTitle}/applications/{email}/status")
    @ResponseStatus(HttpStatus.OK)
    public String getApplicationStatus(@PathVariable(value = "jobTitle") final String jobTitle, @PathVariable(value = "email") final String email) {
        return jobApplicationService.getJobApplicationByPrimaryKey(jobTitle, email).getApplicationStatus().name();
    }

    @GetMapping(value = "/offers/applications/count")
    @ResponseStatus(HttpStatus.OK)
    public long getApplicationsCount() {
        return jobApplicationService.getJobApplicationCount();
    }

    @PatchMapping(value = "/offers/{jobTitle}/applications/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void updateApplicationStatus(@PathVariable(value = "jobTitle") final String jobTitle, @PathVariable(value = "email") final String email, @RequestParam(value = "status") final String status) {
        jobApplicationService.updateJobApplicationStatus(status, jobTitle, email);
    }


    @GetMapping(value = "/offers/{jobTitle}/applications/count")
    @ResponseStatus(HttpStatus.OK)
    public long getApplicationsCount(@PathVariable(value = "jobTitle") final String jobTitle) {
        return jobApplicationService.getJobApllicationCountByJobTitle(jobTitle);
    }


}
