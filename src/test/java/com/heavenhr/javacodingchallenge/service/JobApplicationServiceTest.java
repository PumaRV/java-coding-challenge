package com.heavenhr.javacodingchallenge.service;

import com.heavenhr.javacodingchallenge.model.JobApplication;
import com.heavenhr.javacodingchallenge.model.JobApplicationStatus;
import com.heavenhr.javacodingchallenge.model.Offer;
import com.heavenhr.javacodingchallenge.repository.JobApplicationRepository;
import com.heavenhr.javacodingchallenge.repository.OfferRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JobApplicationServiceTest {
    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private JobApplicationService jobApplicationService;

    @Test
    public void testCreateJobApplication() {
        final Offer offer = mock(Offer.class);
        final JobApplication jobApplication = mock(JobApplication.class);

        final String testJobTitle = "Test title";

        offer.setJobTitle(testJobTitle);
        jobApplication.setJobTitle(testJobTitle);
        jobApplication.setRelatedOffer(offer);

        when(offerRepository.findById(anyString())).thenReturn(Optional.of(offer));
        when(jobApplication.getJobTitle()).thenReturn(testJobTitle);

        jobApplicationService.createJobApplication(jobApplication);

        verify(jobApplicationRepository).save(jobApplication);
    }

    @Test
    public void getJobApplicationsByJobTitleSuccessfully() {
        final JobApplication jobApplication = mock(JobApplication.class);
        jobApplicationService.createJobApplication(jobApplication);
        List<JobApplication> expectedApplications = new ArrayList<>();
        expectedApplications.add(jobApplication);
        final String testJobTitle = "Magician";

        when(jobApplicationRepository.findAllByJobTitle(anyString())).thenReturn(expectedApplications);

        List<JobApplication> actualApplications = jobApplicationService.getJobApplicationsByJobTitle(testJobTitle);

        verify(jobApplicationRepository).findAllByJobTitle(testJobTitle);
        assertEquals(expectedApplications, actualApplications);
    }

    @Test
    public void getJobApplicationsByPrimaryKeySuccessfully() {
        final JobApplication expectedJobApplication = mock(JobApplication.class);
        jobApplicationService.createJobApplication(expectedJobApplication);

        final String testJobTitle = "Magician";
        final String testEmail = "john.travolta@gmail.com";

        when(jobApplicationRepository.findByJobTitleAndCandidateEmail(anyString(), anyString())).thenReturn(expectedJobApplication);

        final JobApplication actualApplication = jobApplicationService.getJobApplicationByPrimaryKey(testJobTitle, testEmail);

        verify(jobApplicationRepository).findByJobTitleAndCandidateEmail(testJobTitle, testEmail);
        assertEquals(expectedJobApplication, actualApplication);
    }

    @Test
    public void getApplicationCountSuccessfully() {

        final long expectedCount = 3L;
        when(jobApplicationRepository.count()).thenReturn(expectedCount);
        final long actualCount = jobApplicationService.getJobApplicationCount();

        verify(jobApplicationRepository).count();
        assertEquals(expectedCount, actualCount);

    }

    @Test
    public void getApplicationCountByJobTitleSuccessfully() {

        final long expectedCount = 2L;
        final String testJobTitle = "Rockstar senior developer";
        when(jobApplicationRepository.countAllByJobTitle(testJobTitle)).thenReturn(expectedCount);
        final long actualCount = jobApplicationService.getJobApllicationCountByJobTitle(testJobTitle);

        verify(jobApplicationRepository).countAllByJobTitle(testJobTitle);
        assertEquals(expectedCount, actualCount);

    }

    @Test
    public void updateJobApplicationStatusSuccessfully() {
        final String testJobTitle = "Bug hunter";
        final String testEmail = "bruce.willis@gmail.com";
        final JobApplication jobApplication = new JobApplication();
        jobApplication.setApplicationStatus(JobApplicationStatus.APPLIED);
        final JobApplicationStatus expectedApplicationStatus = JobApplicationStatus.HIRED;

        when(jobApplicationRepository.findByJobTitleAndCandidateEmail(testJobTitle, testEmail)).thenReturn(jobApplication);

        jobApplicationService.updateJobApplicationStatus("HIRED", testJobTitle, testEmail);
        assertEquals(jobApplication.getApplicationStatus(), expectedApplicationStatus);
        verify(jobApplicationRepository).save(any(JobApplication.class));

    }
}