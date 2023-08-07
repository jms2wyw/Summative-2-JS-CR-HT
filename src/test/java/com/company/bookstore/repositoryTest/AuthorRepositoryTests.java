package com.company.bookstore.repositoryTest;

import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class AuthorRepositoryTests {
    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    public void setUp() {
        authorRepository.deleteAll();
    }

    @Test
    public void shouldAddAuthor() {
        //Arrange...
        Author author = new Author();
        author.setAuthorName("J.K. Rowling");
        author.setBiography("J.K. Rowling is a British author born on July 31, 1965. She is famous for writing the \"Harry Potter\" series, which gained worldwide acclaim and inspired a generation of readers. Her storytelling and imagination have left a lasting impact on literature, making her a literary icon.");

        author = authorRepository.save(author);
        Optional<Author> author1 = authorRepository.findById(author.getId());

        assertEquals(author1.get(), author);
    }
    @Test
    public void shouldGetAuthorById() {

        Author author = new Author();
        author.setAuthorName("J.K. Rowling");
        author.setBiography("J.K. Rowling is a British author born on July 31, 1965. She is famous for writing the \"Harry Potter\" series, which gained worldwide acclaim and inspired a generation of readers. Her storytelling and imagination have left a lasting impact on literature, making her a literary icon.");
        author = authorRepository.save(author);

        Author author2 = new Author();
        author2.setAuthorName("Haruki Murakami");
        author2.setBiography("Haruki Murakami is a celebrated Japanese novelist born on January 12, 1949. His captivating works, blending surrealism and existential themes, have earned him global acclaim and a dedicated fanbase.");
        author2 = authorRepository.save(author2);

        //Act...
        Optional<Author> foundAuthor = authorRepository.findById(author.getId());

        //Assert...
        assertEquals(foundAuthor.get(), author);
    }

    @Test
    public void shouldUpdateAuthor() {

        //Arrange...
        Author author = new Author();
        author.setAuthorName("J.K. Rowling");
        author.setBiography("J.K. Rowling is a British author born on July 31, 1965. She is famous for writing the \"Harry Potter\" series, which gained worldwide acclaim and inspired a generation of readers. Her storytelling and imagination have left a lasting impact on literature, making her a literary icon.");
        author = authorRepository.save(author);

        author.setAuthorName("Joanne Rowling");
        authorRepository.save(author);

        Optional<Author> author1 = authorRepository.findById(author.getId());
        assertEquals(author1.get(), author);
    }

    @Test
    public void shouldGetAllAuthors() {
        Author author = new Author();
        author.setAuthorName("J.K. Rowling");
        author.setBiography("J.K. Rowling is a British author born on July 31, 1965. She is famous for writing the \"Harry Potter\" series, which gained worldwide acclaim and inspired a generation of readers. Her storytelling and imagination have left a lasting impact on literature, making her a literary icon.");
        author = authorRepository.save(author);

        Author author2 = new Author();
        author2.setAuthorName("Haruki Murakami");
        author2.setBiography("Haruki Murakami is a celebrated Japanese novelist born on January 12, 1949. His captivating works, blending surrealism and existential themes, have earned him global acclaim and a dedicated fanbase.");
        author2 = authorRepository.save(author2);

        List<Author> aList = authorRepository.findAll();

        assertEquals(aList.size(), 2);
    }

    @Test
    public void shouldDeleteAuthorById() {

        //Arrange...
        Author author = new Author();
        author.setAuthorName("J.K. Rowling");
        author.setBiography("J.K. Rowling is a British author born on July 31, 1965. She is famous for writing the \"Harry Potter\" series, which gained worldwide acclaim and inspired a generation of readers. Her storytelling and imagination have left a lasting impact on literature, making her a literary icon.");
        author.setId(1);
        author = authorRepository.save(author);

        Optional<Author> author1 = authorRepository.findById(author.getId());
        assertEquals(author1.get(), author);

        authorRepository.deleteById(author.getId());

        author1 = authorRepository.findById(author.getId());
        assertFalse(author1.isPresent());
    }
}
