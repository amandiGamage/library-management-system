package com.example.library.service.impl;

import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for library operations such as registering books/borrowers,
 * borrowing and returning books.
 */
@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowerRepository borrowerRepository;

    /**
     * Registers a new book in the system.
     *
     * @param book Book to be registered
     * @return Saved book entity
     * */
    public Book registerBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Retrieves all books in the system.
     *
     * @return List of all books
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Registers a new borrower in the system.
     *
     * @param borrower Borrower to be registered
     * @return Saved borrower entity
     */
    public Borrower registerBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

}
