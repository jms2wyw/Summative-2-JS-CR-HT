package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private PublisherRepository publisherRepository;

    private List<Book> bookList = new ArrayList<>();

    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    @BeforeEach
    private void setup() {
        Author author = new Author();
        author.setId(1);
        author.setFirstName("John");
        author.setLastName("Smith");
        author.setStreet("83rd Street");
        author.setCity("Queens");
        author.setState("NY");
        author.setPostalCode("11370");
        author.setPhone("646-212-9640");
        author.setEmail("smith@mail.com");

        Author secondAuth = new Author();
        secondAuth.setId(2);
        secondAuth.setFirstName("NotJohn");
        secondAuth.setLastName("NotSmith");
        secondAuth.setStreet("MainSt");
        secondAuth.setCity("NotQueens");
        secondAuth.setState("OH");
        secondAuth.setPostalCode("Not11370");
        secondAuth.setPhone("000-000-0000");
        secondAuth.setEmail("not@mail.com");

        Publisher publisher = new Publisher();
        publisher.setPublisherId(15);
        publisher.setName("BigPub");
        publisher.setStreet("Main St");
        publisher.setCity("Boston");
        publisher.setState("MA");
        publisher.setPostalCode("02108");
        publisher.setPhone("123-456-7891");
        publisher.setEmail("big.pub@mail.com");

        Book book = new Book();
        book.setBookId(1);
        book.setIsbn("12345");
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        book.setBookId(author.getId());
        book.setTitle("The Book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("12.99"));

        Book secondBook = new Book();
        secondBook.setBookId(2);
        secondBook.setIsbn("54321");
        secondBook.setPublishDate(LocalDate.of(2020, 2, 1));
        secondBook.setBookId(author.getId());
        secondBook.setTitle("The Sequel");
        secondBook.setPublisherId(publisher.getPublisherId());
        secondBook.setPrice(new BigDecimal("29.99"));

        Book thirdBook = new Book();
        thirdBook.setBookId(3);
        thirdBook.setIsbn("67890");
        thirdBook.setPublishDate(LocalDate.of(2020, 3, 1));
        thirdBook.setBookId(secondAuth.getId());
        thirdBook.setTitle("Three Times");
        thirdBook.setPublisherId(publisher.getPublisherId());
        thirdBook.setPrice(new BigDecimal("9.99"));

        bookList.add(book);
        bookList.add(secondBook);
        bookList.add(thirdBook);
    }

    @Test
    public void shouldCreateBook() throws Exception {
        Book anotherBook = new Book();
        anotherBook.setBookId(50);
        anotherBook.setIsbn("13579");
        anotherBook.setPublishDate(LocalDate.of(2023, 1, 1));
        anotherBook.setAuthorId(1);
        anotherBook.setTitle("New Book");
        anotherBook.setPublisherId(15);
        anotherBook.setPrice(new BigDecimal("4.99"));

        String inputJson = mapper.writeValueAsString(anotherBook);

        mockMvc.perform(post("/books")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getBooksById() throws Exception {
        Book anotherBook = new Book();
        anotherBook.setBookId(50);
        anotherBook.setIsbn("13579");
        anotherBook.setPublishDate(LocalDate.of(2023, 1, 1));
        anotherBook.setAuthorId(1);
        anotherBook.setTitle("New Book");
        anotherBook.setPublisherId(15);
        anotherBook.setPrice(new BigDecimal("4.99"));

        Book moreBook = new Book();
        moreBook.setBookId(51);
        moreBook.setIsbn("13579");
        moreBook.setPublishDate(LocalDate.of(2023, 1, 1));
        moreBook.setAuthorId(2);
        moreBook.setTitle("New Book");
        moreBook.setPublisherId(15);
        moreBook.setPrice(new BigDecimal("4.99"));

        String outputJson = mapper.writeValueAsString(anotherBook);

        mockMvc.perform(get("/books/50"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllBooks() throws Exception {
        String outputJson = mapper.writeValueAsString(bookList);

        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateBook() throws Exception {
        Book updatedBook = new Book();
        updatedBook.setBookId(3);
        updatedBook.setIsbn("67890");
        updatedBook.setPublishDate(LocalDate.of(2020, 3, 1));
        updatedBook.setAuthorId(1);
        updatedBook.setTitle("New Title");
        updatedBook.setPublisherId(15);
        updatedBook.setPrice(new BigDecimal("9.99"));

        String inputJson = mapper.writeValueAsString(updatedBook);

        mockMvc.perform(put("/books/3")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteBookById() throws Exception {
        mockMvc.perform(delete("/books/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getBooksByAuthorId() throws Exception {
        mockMvc.perform(get("/books/author/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
