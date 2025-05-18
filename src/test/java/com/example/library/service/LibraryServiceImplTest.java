package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.impl.LibraryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

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

        when(bookRepository.save(book)).thenReturn(book);

        Book saved = libraryService.registerBook(book);
        assertEquals("Clean Code", saved.getTitle());
        verify(bookRepository).save(book);
    }
}