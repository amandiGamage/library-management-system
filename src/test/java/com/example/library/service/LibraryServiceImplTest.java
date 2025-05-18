package com.example.library.service;

import com.example.library.exception.BookAlreadyBorrowedException;
import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.model.BorrowingRecord;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;
import com.example.library.repository.BorrowingRecordRepository;
import com.example.library.service.impl.LibraryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryServiceImplTest {

    // Mocked dependencies
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

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

    /**
     * Test case for borrowing a book successfully
     */
    @Test
    void testBorrowBookSuccess() {
        Book book = new Book();
        book.setId(1L);
        book.setBorrowed(false); // Book is available

        Borrower borrower = new Borrower();
        borrower.setId(1L);

        // Mock repository methods
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));
        when(bookRepository.save(book)).thenReturn(book);
        when(borrowingRecordRepository.save(any())).thenReturn(new BorrowingRecord());

        String result = libraryService.borrowBook(1L, 1L);

        // Assert expected success message
        assertEquals("Book borrowed successfully.", result);
    }

    /**
     * Test case for borrowing an already borrowed book
     */
    @Test
    void testBorrowBookAlreadyBorrowed() {
        Book book = new Book();
        book.setId(1L);
        book.setBorrowed(true); // Book is already borrowed

        Borrower borrower = new Borrower();
        borrower.setId(1L);

        // Mock repository methods
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));

        // Expect an exception to be thrown
        assertThrows(BookAlreadyBorrowedException.class,
                () -> libraryService.borrowBook(1L, 1L));
    }

    /**
     * Test case for returning a borrowed book successfully
     */
    @Test
    void testReturnBookSuccess() {
        Book book = new Book();
        book.setId(1L);
        book.setBorrowed(true); // Book is borrowed

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setReturnDate(null); // Not yet returned

        // Mock repository methods
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        when(borrowingRecordRepository.findAll()).thenReturn(List.of(record));
        when(borrowingRecordRepository.save(record)).thenReturn(record);

        String result = libraryService.returnBook(1L, 1L);

        // Assert expected success message
        assertEquals("Book returned successfully.", result);
    }
}