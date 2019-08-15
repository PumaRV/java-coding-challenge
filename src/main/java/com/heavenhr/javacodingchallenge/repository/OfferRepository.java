package com.heavenhr.javacodingchallenge.repository;

import com.heavenhr.javacodingchallenge.model.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, String> {
}
