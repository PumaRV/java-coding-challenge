package com.heavenhr.javacodingchallenge.repository;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class JobApplicationPrimaryKey implements Serializable {
    protected String jobTitle;
    protected String candidateEmail;
}
