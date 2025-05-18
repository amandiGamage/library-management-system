package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing library-related operations such as
 * registering books and borrowers, borrowing books, and returning books.
 */
@RestController
@RequestMapping("/api")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    /**
     * Registers a new book.
     *
     * @param book The book object to be registered.
     * @return The saved book with HTTP 200 OK.
     */
    @PostMapping("/books")
    public ResponseEntity<Book> registerBook(@RequestBody Book book) {
        Book savedBook = libraryService.registerBook(book);
        return ResponseEntity.ok(savedBook);
    }

    /**
     * Retrieves the list of all books.
     *
     * @return List of books with HTTP 200 OK.
     */
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = libraryService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * Registers a new borrower.
     *
     * @param borrower The borrower object to be registered.
     * @return The saved borrower with HTTP 200 OK.
     */
    @PostMapping("/borrowers")
    public ResponseEntity<Borrower> registerBorrower(@RequestBody Borrower borrower) {
        Borrower savedBorrower = libraryService.registerBorrower(borrower);
        return ResponseEntity.ok(savedBorrower);
    }

    /**
     * Borrows a book for a specific borrower.
     *
     * @param borrowerId The ID of the borrower.
     * @param bookId The ID of the book to be borrowed.
     * @return Success message with HTTP 200 OK.
     */
    @PostMapping("/borrowers/{borrowerId}/books/{bookId}/borrow")
    public ResponseEntity<String> borrowBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        String message = libraryService.borrowBook(borrowerId, bookId); // Will throw exceptions if needed
        return ResponseEntity.ok(message);
    }
}
