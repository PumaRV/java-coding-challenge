package com.heavenhr.javacodingchallenge.repository;

import com.heavenhr.javacodingchallenge.model.Offer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class JobApplicationPrimaryKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "jobTitle", nullable = false)
    protected Offer relatedOffer;
    protected String candidateEmail;
}
