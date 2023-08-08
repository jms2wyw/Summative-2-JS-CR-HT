package com.company.bookstore.controllerTest;
import com.company.bookstore.model.Author;

import com.company.bookstore.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {
    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository repo;
    private ObjectMapper mapper = new ObjectMapper();
    private List<Author> authorList = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        Author author = new Author();
        author.setId(1);
        author.setAuthorName("J.K. Rowling");
        author.setBiography("J.K. Rowling is a British author born on July 31, 1965. She is famous for writing the \"Harry Potter\" series, which gained worldwide acclaim and inspired a generation of readers. Her storytelling and imagination have left a lasting impact on literature, making her a literary icon.");

        Author author2 = new Author();
        author2.setId(2);
        author2.setAuthorName("Haruki Murakami");
        author2.setBiography("Haruki Murakami is a celebrated Japanese novelist born on January 12, 1949. His captivating works, blending surrealism and existential themes, have earned him global acclaim and a dedicated fanbase.");

        authorList.add(author);
        authorList.add(author2);
    }

    // Testing GET /authors
    @Test
    public void shouldReturnAllAuthorsInCollection() throws Exception {

        // ARRANGE
        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(authorList);

        // ACT
        mockMvc.perform(get("/authors"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }

    // Testing POST /authors
    @Test
    public void shouldReturnNewAuthor() throws Exception {
        Author inputAuthor = new Author();
        inputAuthor.setAuthorName("J.K. Rowling");
        inputAuthor.setBiography("J.K. Rowling is a British author born on July 31, 1965. She is famous for writing the \"Harry Potter\" series, which gained worldwide acclaim and inspired a generation of readers. Her storytelling and imagination have left a lasting impact on literature, making her a literary icon.");

        String inputJson = mapper.writeValueAsString(inputAuthor);

        mockMvc.perform(post("/authors")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    // Testing GET authors/{id}
    @Test
    public void shouldReturnAuthorById() throws Exception {

        Author outputAuthor = new Author();
        outputAuthor.setAuthorName("J.K. Rowling");
        outputAuthor.setBiography("J.K. Rowling is a British author born on July 31, 1965. She is famous for writing the \"Harry Potter\" series, which gained worldwide acclaim and inspired a generation of readers. Her storytelling and imagination have left a lasting impact on literature, making her a literary icon.");
        outputAuthor.setId(1);

        Author outputAuthor2 = new Author();
        outputAuthor2.setId(2);
        outputAuthor2.setAuthorName("Haruki Murakami");
        outputAuthor2.setBiography("Haruki Murakami is a celebrated Japanese novelist born on January 12, 1949. His captivating works, blending surrealism and existential themes, have earned him global acclaim and a dedicated fanbase.");


        String outputJson = mapper.writeValueAsString(outputAuthor);

        mockMvc.perform(get("/authors/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing PUT /authors/{id}
    @Test
    public void shouldUpdateAuthor() throws Exception {
       Author inputAuthor = new Author();
       inputAuthor.setAuthorName("Mrs. Rowling");
       inputAuthor.setBiography("J.K. Rowling is a British author born on July 31, 1965. She is famous for writing the \"Harry Potter\" series, which gained worldwide acclaim and inspired a generation of readers. Her storytelling and imagination have left a lasting impact on literature, making her a literary icon.");
       inputAuthor.setId(3);

        String inputJson = mapper.writeValueAsString(inputAuthor);

        mockMvc.perform(put("/authors/3")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /authors/{id}
    @Test
    public void shouldDeleteAuthorById() throws Exception {
        mockMvc.perform(delete("/authors/2"))
                .andExpect(status().isNoContent());
    }
}
