package com.heavenhr.javacodingchallenge.service;

import com.heavenhr.javacodingchallenge.model.Offer;
import com.heavenhr.javacodingchallenge.repository.OfferRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;


    @InjectMocks
    private OfferService offerService;

    @Test
    public void createOfferSuccessfully() {
        final Offer offer = Mockito.mock(Offer.class);
        offerService.createOffer(offer);

        verify(offerRepository).save(offer);
    }

    @Test
    public void getAllOffersSuccessfully(){
        final Offer offer = Mockito.mock(Offer.class);
        offerService.createOffer(offer);
        List<Offer> expectedOffers = new ArrayList<>();
        expectedOffers.add(offer);
        when(offerRepository.findAll()).thenReturn(expectedOffers);

        List<Offer> actualOffers = offerService.getAllOffers();

        verify(offerRepository).findAll();
        assertEquals(expectedOffers, actualOffers);
    }
}