package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {

    @Autowired
    PublisherRepository repo;

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher (@RequestBody Publisher Publisher) {
        return repo.save(Publisher);
    }

    @GetMapping("/publishers/{id}")
    public Publisher getPublisherById(@PathVariable int id){
        Optional<Publisher> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/publishers")
    public List<Publisher> getPublishers() {
        return repo.findAll();
    }

    @PutMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher (@RequestBody Publisher Publisher,@PathVariable int id) {
        repo.save(Publisher);
    }

    @DeleteMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int id){
        repo.deleteById(id);
    }
}
