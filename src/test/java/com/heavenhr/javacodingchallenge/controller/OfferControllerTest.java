package com.heavenhr.javacodingchallenge.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class OfferControllerTest {
    //I would liked to have created separate files for the json content. I didn't do it due to time constraints

    private MockMvc mockMvc;

    @Autowired
    private OfferController offerController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.offerController).build();

        final String offerJson = " {\n" +
                "        \"jobTitle\": \"Rockstar\",\n" +
                "        \"startDate\": \"2019-06-05\"\n" +
                "    }";
        mockMvc.perform(post("/offers").contentType(MediaType.APPLICATION_JSON).content(offerJson));

        final String offerJson2 = " {\n" +
                "        \"jobTitle\": \"Wizard\",\n" +
                "        \"startDate\": \"2019-06-05\"\n" +
                "    }";
        mockMvc.perform(post("/offers").contentType(MediaType.APPLICATION_JSON).content(offerJson2));

        final String applicationJson ="{\n" +
                "\"candidateEmail\": \"jose@gmail.com\", \n" +
                "\"resumeText\": \"This guy is really awesome! You should hire him\", \n" +
                "\"applicationStatus\": \"APPLIED\"\n" +
                "}";

        mockMvc.perform(post("/offers/Rockstar/applications").contentType(MediaType.APPLICATION_JSON).content(applicationJson));

        final String applicationJson2 ="{\n" +
                "\"candidateEmail\": \"otto@gmail.com\", \n" +
                "\"resumeText\": \"This guy is also awesome! You should hire him\", \n" +
                "\"applicationStatus\": \"APPLIED\"\n" +
                "}";

        mockMvc.perform(post("/offers/Rockstar/applications").contentType(MediaType.APPLICATION_JSON).content(applicationJson2));

        final String applicationJson3 ="{\n" +
                "\"candidateEmail\": \"otto@gmail.com\", \n" +
                "\"resumeText\": \"This guy is also awesome! You should hire him\", \n" +
                "\"applicationStatus\": \"APPLIED\"\n" +
                "}";

        mockMvc.perform(post("/offers/Wizard/applications").contentType(MediaType.APPLICATION_JSON).content(applicationJson3));


    }


    @Test
    public void testCreateOffer() throws Exception {
        final String json = " {\n" +
                "        \"jobTitle\": \"Senior Software Engineer\",\n" +
                "        \"startDate\": \"2019-06-05\"\n" +
                "    }";
        mockMvc.perform(post("/offers").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllOffers() throws Exception{
        mockMvc.perform(get("/offers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*.jobTitle").isArray());
    }

    @Test
    public void testCreateJobApplication() throws Exception{
        final String json ="{\n" +
                "\"candidateEmail\": \"otto.rodriguez.v@gmail.com\", \n" +
                "\"resumeText\": \"This guy is really awesome! You should hire him ASAP\", \n" +
                "\"applicationStatus\": \"APPLIED\"\n" +
                "}";

        mockMvc.perform(post("/offers/Rockstar/applications").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void getApplicationsByOffer() throws Exception{
        final String testJobTitle = "Rockstar";

        mockMvc.perform(get("/offers/"+testJobTitle+"/applications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getApplicationStatus() throws Exception{
        final String testJobTitle = "Rockstar";
        final String testEmail = "jose@gmail.com";

        mockMvc.perform(get("/offers/"+testJobTitle+"/applications/"+testEmail+"/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("APPLIED"));
    }

    @Test
    public void getApplicationCount() throws Exception{
        mockMvc.perform(get("/offers/applications/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(3));
    }

    @Test
    public void getApplicationCountByOffer() throws Exception{
        final String testJobTitle = "Rockstar";


        mockMvc.perform(get("/offers/"+testJobTitle+"/applications/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(2));
    }



}