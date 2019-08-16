package com.heavenhr.javacodingchallenge.service;

import com.heavenhr.javacodingchallenge.model.JobApplication;
import com.heavenhr.javacodingchallenge.model.JobApplicationStatus;
import com.heavenhr.javacodingchallenge.model.Offer;
import com.heavenhr.javacodingchallenge.repository.JobApplicationRepository;
import com.heavenhr.javacodingchallenge.repository.OfferRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {
    private static final Logger LOGGER = LogManager.getLogger();

    private JobApplicationRepository jobApplicationRepository;
    private OfferRepository offerRepository;

    public JobApplicationService(final JobApplicationRepository applicationRepository, final OfferRepository offerRepository) {
        this.jobApplicationRepository = applicationRepository;
        this.offerRepository = offerRepository;
    }

    public void createJobApplication(final JobApplication jobApplication) {
        final Optional<Offer> optionalOffer = offerRepository.findById(jobApplication.getJobTitle());
        if (optionalOffer.isPresent()) {
            final Offer offer = optionalOffer.get();
            jobApplication.setRelatedOffer(offer);
            jobApplicationRepository.save(jobApplication);
        }
    }

    public List<JobApplication> getJobApplicationsByJobTitle(final String jobTitle) {
        return jobApplicationRepository.findAllByJobTitle(jobTitle);
    }

    public long getJobApllicationCountByJobTitle(final String jobTitle){
        return jobApplicationRepository.countAllByJobTitle(jobTitle);
    }

    public JobApplication getJobApplicationByPrimaryKey(final String jobTitle, final String email) {
        return jobApplicationRepository.findByJobTitleAndCandidateEmail(jobTitle, email);
    }

    public long getJobApplicationCount() {
        return jobApplicationRepository.count();
    }

    public void updateJobApplicationStatus(final String status, final String jobTitle, final String email) {
        final JobApplicationStatus jobApplicationStatus = JobApplicationStatus.valueOf(status);
        final JobApplication jobApplication = jobApplicationRepository.findByJobTitleAndCandidateEmail(jobTitle, email);
        jobApplication.setApplicationStatus(jobApplicationStatus);
        jobApplicationRepository.save(jobApplication);
        switch (jobApplicationStatus) { // Add different business scenarios according to status
            case APPLIED:
            case HIRED:
            case INVITED:
            case REJECTED:
                LOGGER.info("The status of the application for " + jobTitle +" position from applicant " + email + " was updated to: " + status);
        }
    }

}
