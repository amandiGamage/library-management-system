package com.example.library.controller;

import com.example.library.model.Book;
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
}
