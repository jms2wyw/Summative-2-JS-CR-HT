package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @BeforeEach
    public void setup() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    @Test
    public void shouldAddBook() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Smith");
        author.setStreet("83rd Street");
        author.setCity("Queens");
        author.setState("NY");
        author.setPostalCode("11370");
        author.setPhone("646-212-9640");
        author.setEmail("smith@mail.com");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher();
        publisher.setName("BigPub");
        publisher.setStreet("Main St");
        publisher.setCity("Boston");
        publisher.setState("MA");
        publisher.setPostalCode("02108");
        publisher.setPhone("123-456-7891");
        publisher.setEmail("big.pub@mail.com");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setIsbn("12345");
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("The Book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("12.99"));
        book = bookRepository.save(book);

        Optional<Book> created = bookRepository.findById(book.getBookId());

        assertEquals(created.get(), book);
    }

    @Test
    public void shouldReadById() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Smith");
        author.setStreet("83rd Street");
        author.setCity("Queens");
        author.setState("NY");
        author.setPostalCode("11370");
        author.setPhone("646-212-9640");
        author.setEmail("smith@mail.com");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher();
        publisher.setName("BigPub");
        publisher.setStreet("Main St");
        publisher.setCity("Boston");
        publisher.setState("MA");
        publisher.setPostalCode("02108");
        publisher.setPhone("123-456-7891");
        publisher.setEmail("big.pub@mail.com");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setIsbn("12345");
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("The Book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("12.99"));
        book = bookRepository.save(book);

        Book secondBook = new Book();
        secondBook.setIsbn("54321");
        secondBook.setPublishDate(LocalDate.of(2020, 2, 1));
        secondBook.setAuthorId(author.getAuthorId());
        secondBook.setTitle("The Sequel");
        secondBook.setPublisherId(publisher.getPublisherId());
        secondBook.setPrice(new BigDecimal("29.99"));
        secondBook = bookRepository.save(secondBook);

        Optional<Book> target = bookRepository.findById(secondBook.getBookId());

        assertEquals(target.get(), secondBook);

    }

    @Test
    public void getAllBooks() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Smith");
        author.setStreet("83rd Street");
        author.setCity("Queens");
        author.setState("NY");
        author.setPostalCode("11370");
        author.setPhone("646-212-9640");
        author.setEmail("smith@mail.com");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher();
        publisher.setName("BigPub");
        publisher.setStreet("Main St");
        publisher.setCity("Boston");
        publisher.setState("MA");
        publisher.setPostalCode("02108");
        publisher.setPhone("123-456-7891");
        publisher.setEmail("big.pub@mail.com");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setIsbn("12345");
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("The Book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("12.99"));
        book = bookRepository.save(book);

        Book secondBook = new Book();
        secondBook.setIsbn("54321");
        secondBook.setPublishDate(LocalDate.of(2020, 2, 1));
        secondBook.setAuthorId(author.getAuthorId());
        secondBook.setTitle("The Sequel");
        secondBook.setPublisherId(publisher.getPublisherId());
        secondBook.setPrice(new BigDecimal("29.99"));
        secondBook = bookRepository.save(secondBook);

        Book thirdBook = new Book();
        thirdBook.setIsbn("67891");
        thirdBook.setPublishDate(LocalDate.of(2020, 3, 1));
        thirdBook.setAuthorId(author.getAuthorId());
        thirdBook.setTitle("Three Times");
        thirdBook.setPublisherId(publisher.getPublisherId());
        thirdBook.setPrice(new BigDecimal("9.99"));
        thirdBook = bookRepository.save(thirdBook);

        List<Book> allBooks = bookRepository.findAll();

        assertEquals(allBooks.size(), 3);
    }

    @Test
    public void shouldUpdateBook() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Smith");
        author.setStreet("83rd Street");
        author.setCity("Queens");
        author.setState("NY");
        author.setPostalCode("11370");
        author.setPhone("646-212-9640");
        author.setEmail("smith@mail.com");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher();
        publisher.setName("BigPub");
        publisher.setStreet("Main St");
        publisher.setCity("Boston");
        publisher.setState("MA");
        publisher.setPostalCode("02108");
        publisher.setPhone("123-456-7891");
        publisher.setEmail("big.pub@mail.com");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setIsbn("12345");
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("The Book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("12.99"));
        book = bookRepository.save(book);

        book.setTitle("Revised The Book");
        book.setPrice(new BigDecimal("14.99"));
        bookRepository.save(book);

        Optional<Book> updated = bookRepository.findById(book.getBookId());
        assertEquals(updated.get(), book);
    }

    @Test
    public void shouldDeleteBookById() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Smith");
        author.setStreet("83rd Street");
        author.setCity("Queens");
        author.setState("NY");
        author.setPostalCode("11370");
        author.setPhone("646-212-9640");
        author.setEmail("smith@mail.com");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher();
        publisher.setName("BigPub");
        publisher.setStreet("Main St");
        publisher.setCity("Boston");
        publisher.setState("MA");
        publisher.setPostalCode("02108");
        publisher.setPhone("123-456-7891");
        publisher.setEmail("big.pub@mail.com");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setIsbn("12345");
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("The Book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("12.99"));
        book = bookRepository.save(book);

        Optional<Book> checkPresent = bookRepository.findById(book.getBookId());
        assertEquals(checkPresent.get(), book);

        bookRepository.deleteById(book.getBookId());

        checkPresent = bookRepository.findById(book.getBookId());

        assertFalse(checkPresent.isPresent());
    }

    @Test
    public void shouldFindBooksByAuthorId() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Smith");
        author.setStreet("83rd Street");
        author.setCity("Queens");
        author.setState("NY");
        author.setPostalCode("11370");
        author.setPhone("646-212-9640");
        author.setEmail("smith@mail.com");
        author = authorRepository.save(author);

        Author secondAuth = new Author();
        secondAuth.setFirstName("NotJohn");
        secondAuth.setLastName("NotSmith");
        secondAuth.setStreet("MainSt");
        secondAuth.setCity("NotQueens");
        secondAuth.setState("OH");
        secondAuth.setPostalCode("Not11370");
        secondAuth.setPhone("000-000-0000");
        secondAuth.setEmail("not@mail.com");
        secondAuth = authorRepository.save(secondAuth);

        Publisher publisher = new Publisher();
        publisher.setName("BigPub");
        publisher.setStreet("Main St");
        publisher.setCity("Boston");
        publisher.setState("MA");
        publisher.setPostalCode("02108");
        publisher.setPhone("123-456-7891");
        publisher.setEmail("big.pub@mail.com");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setIsbn("12345");
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("The Book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("12.99"));
        book = bookRepository.save(book);

        Book secondBook = new Book();
        secondBook.setIsbn("54321");
        secondBook.setPublishDate(LocalDate.of(2020, 2, 1));
        secondBook.setAuthorId(author.getAuthorId());
        secondBook.setTitle("The Sequel");
        secondBook.setPublisherId(publisher.getPublisherId());
        secondBook.setPrice(new BigDecimal("29.99"));
        secondBook = bookRepository.save(secondBook);

        Book thirdBook = new Book();
        thirdBook.setIsbn("67890");
        thirdBook.setPublishDate(LocalDate.of(2020, 3, 1));
        thirdBook.setAuthorId(secondAuth.getAuthorId());
        thirdBook.setTitle("Three Times");
        thirdBook.setPublisherId(publisher.getPublisherId());
        thirdBook.setPrice(new BigDecimal("9.99"));
        thirdBook = bookRepository.save(thirdBook);

        List<Book> authorBooks = bookRepository.findAllByAuthorId(author.getAuthorId());

        assertEquals(authorBooks.size(), 2);
    }
}
