package com.heavenhr.javacodingchallenge.service;

import com.heavenhr.javacodingchallenge.model.JobApplication;
import com.heavenhr.javacodingchallenge.model.Offer;
import com.heavenhr.javacodingchallenge.repository.JobApplicationRepository;
import com.heavenhr.javacodingchallenge.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JobApplicationService {
    private JobApplicationRepository jobApplicationRepository;
    private OfferRepository offerRepository;

    public JobApplicationService(final JobApplicationRepository applicationRepository, final OfferRepository offerRepository){
        this.jobApplicationRepository = applicationRepository;
        this.offerRepository = offerRepository;
    }

    public void createJobApplication(final JobApplication jobApplication){
        final Optional<Offer> optionalOffer = offerRepository.findById(jobApplication.getJobTitle());
        if(optionalOffer.isPresent()){
            final Offer offer = optionalOffer.get();
            jobApplication.setRelatedOffer(offer);
            jobApplicationRepository.save(jobApplication);
        }
    }

    public List<JobApplication> getJobApplicationsByJobTitle(final String jobTitle){
        return jobApplicationRepository.findAllByJobTitle(jobTitle);
    }

    public JobApplication getJobApplicationByPrimaryKey(final String jobTitle, final String email){
        return jobApplicationRepository.findByJobTitleAndCandidateEmail(jobTitle, email);
    }
}
