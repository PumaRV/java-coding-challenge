package com.heavenhr.javacodingchallenge.model;

import lombok.Data;
import org.hibernate.annotations.Formula;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Embeddable
public class Offer {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(unique = true)
    protected String jobTitle;

    @NotNull
    protected LocalDate startDate;

    @Formula("(select count(*) from job_application ja where ja.job_title = job_title )")
    protected int numberOfApplications;

}
