package com.company.bookstore.controller;

import com.company.bookstore.model.Book;
import com.company.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class GraphController {
    @Autowired
    BookRepository bookRepository;

    @QueryMapping
    public Book findBookById(@Argument int id) {
        Optional<Book> target = bookRepository.findById(id);

        return target.isPresent() ? target.get() : null;
    }
}
