package com.company.bookstore.repository;

import com.company.bookstore.model.Publisher;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublisherRepositoryTest {
    @Autowired
    PublisherRepository publisherRepository;

    @BeforeEach
    public void setUp() throws Exception{
        publisherRepository.deleteAll();
    }

    @Test
    public void shouldAddPublisher(){
        Publisher publisher  = new Publisher();
        publisher.setName("Jose");
        publisher.setCity("Bronx");
        publisher.setEmail("123@gmail.com");
        publisher.setPhone("1234567890");
        publisher.setPostalCode("10000");
        publisher.setStreet("1500 something St");
        publisher.setState("NY");

        publisher = publisherRepository.save(publisher);

        Optional<Publisher> publisher1 = publisherRepository.findById(publisher.getPublisherId());

        assertEquals(publisher1.get(), publisher);
    }
    @Test
    public void shouldGetPublisher() {
        Publisher publisher1 = new Publisher();
        publisher1.setName("Jose");
        publisher1.setCity("Bronx");
        publisher1.setEmail("123@gmail.com");
        publisher1.setPhone("1234567890");
        publisher1.setPostalCode("10000");
        publisher1.setStreet("1500 something St");
        publisher1.setState("NY");

        publisher1 = publisherRepository.save(publisher1);

        Publisher publisher2 = new Publisher();
        publisher2.setName("bob");
        publisher2.setCity("Bronxxx");
        publisher2.setEmail("12333@gmail.com");
        publisher2.setPhone("1234567890");
        publisher2.setPostalCode("10000");
        publisher2.setStreet("1500 something St");
        publisher2.setState("NY");

        publisher2 = publisherRepository.save(publisher2);

        Optional<Publisher> foundPublisher = publisherRepository.findById(publisher1.getPublisherId());

        assertEquals(foundPublisher.get(), publisher1);
    }

    @Test
    public void shouldGetAllPublishers() {
        Publisher publisher1 = new Publisher();
        publisher1.setName("Jose");
        publisher1.setCity("Bronx");
        publisher1.setEmail("123@gmail.com");
        publisher1.setPhone("1234567890");
        publisher1.setPostalCode("10000");
        publisher1.setStreet("1500 something St");
        publisher1.setState("NY");

        publisher1 = publisherRepository.save(publisher1);

        publisher1 = new Publisher();
        publisher1.setName("Jo");
        publisher1.setCity("Bronx");
        publisher1.setEmail("123@gmail.com");
        publisher1.setPhone("1234567890");
        publisher1.setPostalCode("10000");
        publisher1.setStreet("1500 something St");
        publisher1.setState("NY");

        publisher1 = publisherRepository.save(publisher1);

        List<Publisher> publishers = publisherRepository.findAll();
        assertEquals(publishers.size(), 2);
    }
    @Test
    public void shouldUpdatePublisher(){
        Publisher publisher1 = new Publisher();
        publisher1.setName("Jose");
        publisher1.setCity("Bronx");
        publisher1.setEmail("123@gmail.com");
        publisher1.setPhone("1234567890");
        publisher1.setPostalCode("10000");
        publisher1.setStreet("1500 something St");
        publisher1.setState("NY");

        publisher1 = publisherRepository.save(publisher1);

        publisher1.setName("new name");
        publisher1.setState("VA");
        publisher1.setCity("Charlottesville");

        publisherRepository.save(publisher1);

        Optional<Publisher> foundPublisher = publisherRepository.findById(publisher1.getPublisherId());

        assertEquals(foundPublisher.get(), publisher1);
    }

    @Test
    public void shouldDeletePublisherById(){
        Publisher publisher1 = new Publisher();
        publisher1.setName("Jose");
        publisher1.setCity("Bronx");
        publisher1.setEmail("123@gmail.com");
        publisher1.setPhone("1234567890");
        publisher1.setPostalCode("10000");
        publisher1.setStreet("1500 something St");
        publisher1.setState("NY");

        publisher1 = publisherRepository.save(publisher1);

        Optional<Publisher> publisher2 = publisherRepository.findById(publisher1.getPublisherId());
        assertEquals(publisher2.get(), publisher1);
        publisherRepository.deleteById(publisher1.getPublisherId());
        publisher2 = publisherRepository.findById(publisher1.getPublisherId());
        assertFalse(publisher2.isPresent());
    }

}