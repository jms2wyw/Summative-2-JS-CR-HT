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

        Author author2 = new Author();
        author2.setId(2);
        author2.setFirstName("NotJohn");
        author2.setLastName("NotSmith");
        author2.setStreet("MainSt");
        author2.setCity("NotQueens");
        author2.setState("OH");
        author2.setPostalCode("Not11370");
        author2.setPhone("000-000-0000");
        author2.setEmail("not@mail.com");

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

        String inputJson = mapper.writeValueAsString(author);

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

        Author author2 = new Author();
        author2.setId(2);
        author2.setFirstName("NotJohn");
        author2.setLastName("NotSmith");
        author2.setStreet("MainSt");
        author2.setCity("NotQueens");
        author2.setState("OH");
        author2.setPostalCode("Not11370");
        author2.setPhone("000-000-0000");
        author2.setEmail("not@mail.com");


        String outputJson = mapper.writeValueAsString(author);

        mockMvc.perform(get("/authors/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing PUT /authors/{id}
    @Test
    public void shouldUpdateAuthor() throws Exception {
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

        Author author2 = new Author();
        author2.setId(2);
        author2.setFirstName("NotJohn");
        author2.setLastName("NotSmith");
        author2.setStreet("MainSt");
        author2.setCity("NotQueens");
        author2.setState("OH");
        author2.setPostalCode("Not11370");
        author2.setPhone("000-000-0000");
        author2.setEmail("not@mail.com");

        String inputJson = mapper.writeValueAsString(author);

        mockMvc.perform(put("/authors/1")
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
