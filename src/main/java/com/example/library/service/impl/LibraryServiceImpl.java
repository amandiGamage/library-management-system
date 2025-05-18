package com.example.library.service.impl;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for library operations such as registering books/borrowers,
 * borrowing and returning books.
 */
@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * Registers a new book in the system.
     *
     * @param book Book to be registered
     * @return Saved book entity
     * */
    public Book registerBook(Book book) {
        return bookRepository.save(book);
    }
}
