package com.heavenhr.javacodingchallenge.model;

import com.heavenhr.javacodingchallenge.repository.JobApplicationPrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode
@IdClass(JobApplicationPrimaryKey.class)
public class JobApplication  {
    @Id
    @ManyToOne
    @JoinColumn(name = "jobTitle", nullable = false)
    private Offer relatedOffer;
    @Id
    private String candidateEmail;

    private String resumeText;
    private JobApplicationStatus applicationStatus;
}