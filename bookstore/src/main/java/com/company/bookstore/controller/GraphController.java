package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class GraphController {
    @Autowired
    AuthorRepository repo;

    @QueryMapping
    public Author findAuthorById(@Argument int id){
        Optional<Author> target = repo.findById(id);
        return target.isPresent() ? target.get() : null;
    }
}