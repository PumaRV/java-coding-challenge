package com.heavenhr.javacodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heavenhr.javacodingchallenge.repository.JobApplicationPrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode
@IdClass(JobApplicationPrimaryKey.class)
public class JobApplication {
    @Id
    private String jobTitle;

    @Id
    private String candidateEmail;

    @ManyToOne(targetEntity = Offer.class)
    @JoinColumn(name = "jobTitle", insertable = false, updatable = false)
    private Offer relatedOffer;

    private String resumeText;

    @Enumerated(EnumType.STRING)
    private JobApplicationStatus applicationStatus;

    @JsonIgnore
    public Offer getRelatedOffer() {
        return relatedOffer;
    }

    @JsonIgnore
    public void setRelatedOffer(final Offer relatedOffer) {
        this.relatedOffer = relatedOffer;
    }
}