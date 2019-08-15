package com.heavenhr.javacodingchallenge.service;

import com.heavenhr.javacodingchallenge.model.Offer;
import com.heavenhr.javacodingchallenge.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    public OfferService(final OfferRepository offerRepository){
        this.offerRepository = offerRepository;
    }

    public void createOffer(final Offer offer){
        offerRepository.save(offer);
    }

    public List<Offer> getAllOffers(){
        return (List<Offer>) offerRepository.findAll();
    }
}
