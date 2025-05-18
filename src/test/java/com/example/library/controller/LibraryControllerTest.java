package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.any;


/**
 * This class tests the LibraryController endpoints using @WebMvcTest
 * It focuses on the web layer, mocking the service layer.
 */
@WebMvcTest(LibraryController.class)
public class LibraryControllerTest {
    // Used to perform HTTP requests in tests
    @Autowired
    private MockMvc mockMvc;

    // Mocked version of LibraryService that the controller depends on
    @MockBean
    private LibraryService libraryService;

    /**
     * Test GET /books endpoint
     * Ensures it returns a list of books and HTTP 200 status
     */
    @Test
    void testGetAllBooks() throws Exception {
        // Mock the service to return a list of 2 books
        Mockito.when(libraryService.getAllBooks()).thenReturn(List.of(new Book(), new Book()));

        // Perform GET request and validate response
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk()) // Expect HTTP 200
                .andExpect(jsonPath("$.length()").value(1)); // Expect 2 items in the JSON array
    }

    /**
     * Test POST /books to register a new book
     */
    @Test
    void testRegisterBook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Sample");

        Mockito.when(libraryService.registerBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Sample\",\"author\":\"Author\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample"));
    }

    /**
     * Test POST /borrowers to register a new borrower
     */
    @Test
    void testRegisterBorrower() throws Exception {
        Borrower borrower = new Borrower();
        borrower.setId(1L);
        borrower.setName("John");

        Mockito.when(libraryService.registerBorrower(any(Borrower.class))).thenReturn(borrower);

        mockMvc.perform(post("/borrowers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }
}
