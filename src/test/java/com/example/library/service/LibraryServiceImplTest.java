package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.impl.LibraryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryServiceImplTest {

    // Mocked dependencies
    @Mock
    private BookRepository bookRepository;

    // The service class with mocked dependencies injected
    @InjectMocks
    private LibraryServiceImpl libraryService;

    // Initialize mocks before each test
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for registering a book
     */
    @Test
    void testRegisterBook() {
        Book book = new Book();
        book.setTitle("Clean Code");

        // Mock behavior: return the same book on save
        when(bookRepository.save(book)).thenReturn(book);

        Book saved = libraryService.registerBook(book);
        // Assert the returned book title is as expected
        assertEquals("Clean Code", saved.getTitle());
        // Verify that save was called once
        verify(bookRepository).save(book);
    }

    /**
     * Test case for retrieving all books
     */
    @Test
    void testGetAllBooks() {
        // Create a list of two books
        List<Book> books = List.of(new Book(), new Book());

        // Mock findAll to return this list
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = libraryService.getAllBooks();

        // Assert that the result has size 2
        assertEquals(2, result.size());
    }
}