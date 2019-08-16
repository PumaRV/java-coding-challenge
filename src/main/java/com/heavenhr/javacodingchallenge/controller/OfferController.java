package com.heavenhr.javacodingchallenge.controller;

import com.heavenhr.javacodingchallenge.model.Offer;
import com.heavenhr.javacodingchallenge.service.OfferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OfferController {
    private static final Logger LOGGER = LogManager.getLogger();

    private final OfferService offerService;

    public OfferController(final OfferService offerService){
        this.offerService = offerService;
    }

    @PostMapping(value = "/offers", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createJobOffer(@RequestBody final Offer offer, final BindingResult bindingResult){
        if (bindingResult.hasErrors() || offer == null) {
            throw new IllegalArgumentException("Error while trying to parse data");
        }
    offerService.createOffer(offer);
    }

    @GetMapping(value = "/offers")
    @ResponseStatus(HttpStatus.OK)
    public List<Offer> getAllOffers(){
        return offerService.getAllOffers();
    }
}
