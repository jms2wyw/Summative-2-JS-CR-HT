package com.company.bookstore.controller;

import com.company.bookstore.model.Book;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/books/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable int bookId) {
        Optional<Book> target = bookRepository.findById(bookId);

        return target.isPresent() ? target.get() : null;
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PutMapping("/books/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book, @PathVariable int bookId) {
        bookRepository.save(book);
    }

    @DeleteMapping("/books/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int bookId) {
        bookRepository.deleteById(bookId);
    }

    @GetMapping("/books/author/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAuthorBooks(@PathVariable int authorId) {
        return bookRepository.findAllByAuthorId(authorId);
    }
}
