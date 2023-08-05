package com.company.bookstore.repository;

import com.company.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookRepository  extends JpaRepository<Book, Integer> {
    List<Book> findAllByAuthorId(int id);
}
