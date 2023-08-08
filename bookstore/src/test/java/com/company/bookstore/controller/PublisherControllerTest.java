package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PublisherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PublisherRepository repo;
    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    @BeforeEach
    public void setUp(){
        Publisher publisher  = new Publisher();
        publisher.setName("Jose");
        publisher.setPublisherId(1);
        publisher.setCity("Bronx");
        publisher.setEmail("123@gmail.com");
        publisher.setPhone("1234567890");
        publisher.setPostalCode("10000");
        publisher.setStreet("1500 something St");
        publisher.setState("NY");
    }

    @Test
    public void addPublisher() throws Exception{
        Publisher publisher1  = new Publisher();
        publisher1.setName("Jo");
        publisher1.setPublisherId(34);
        publisher1.setCity("Bronx");
        publisher1.setEmail("123@gmail.com");
        publisher1.setPhone("1234567890");
        publisher1.setPostalCode("10000");
        publisher1.setStreet("1500 something St");
        publisher1.setState("NY");

        String inputJson = mapper.writeValueAsString(publisher1);
        mockMvc.perform(post("/publishers")
                .content(inputJson)

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void getPublisherById() throws Exception{
        Publisher publisher1  = new Publisher();
        publisher1.setName("Jo");
        publisher1.setPublisherId(99);
        publisher1.setCity("Bronx");
        publisher1.setEmail("123@gmail.com");
        publisher1.setPhone("1234567890");
        publisher1.setPostalCode("10000");
        publisher1.setStreet("1500 something St");
        publisher1.setState("NY");

        Publisher publisher2  = new Publisher();
        publisher2.setName("Joseph");
        publisher2.setPublisherId(100);
        publisher2.setCity("sum");
        publisher2.setEmail("123@gmail.com");
        publisher2.setPhone("1234567890");
        publisher2.setPostalCode("11111");
        publisher2.setStreet("1501 something St");
        publisher2.setState("LA");

        String inputJson = mapper.writeValueAsString(publisher1);
        mockMvc.perform(get("/publishers/1"))
                        .andDo(print())
                .andExpect(status().isOk());

    }


    @Test
    public void getPublishers() throws Exception{
        mockMvc.perform(get("/publishers"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void updatePublisher() throws Exception{
        Publisher publisher1  = new Publisher();
        publisher1.setName("Jover");
        publisher1.setPublisherId(1);
        publisher1.setCity("Bronx");
        publisher1.setEmail("123@gmail.com");
        publisher1.setPhone("1234567890");
        publisher1.setPostalCode("10000");
        publisher1.setStreet("150434 something St");
        publisher1.setState("NY");

        String inputJson = mapper.writeValueAsString(publisher1);
        mockMvc.perform(put("/publishers/1")
                        .content(inputJson)

                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deletePublisher() throws Exception{
        mockMvc.perform(delete("/publishers/1"))
                .andExpect(status().isNoContent());
    }
    }
