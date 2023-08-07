package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {
    @Autowired
    AuthorRepository repo;

    //Create
    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author) {
        return repo.save(author);
    }

    //Get All Authors
    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAuthors() {
        return repo.findAll();
    }

    //Read Author by ID
    @GetMapping("/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthorById (@PathVariable int id) {
        Optional<Author> returnVal = repo.findById(id);
        return returnVal.isPresent() ? returnVal.get() : null;
    }

    //Update
    @PutMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Author updateAuthor(@RequestBody Author author, @PathVariable int id) {
        return repo.save(author);
    }

    //Delete
    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id) {
        repo.deleteById(id);
    }

}
