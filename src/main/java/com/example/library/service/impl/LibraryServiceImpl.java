package com.example.library.service.impl;

import com.example.library.exception.BookAlreadyBorrowedException;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.model.BorrowingRecord;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;
import com.example.library.repository.BorrowingRecordRepository;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

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

    /**
     * Handles the borrowing of a book by a borrower.
     *
     * @param borrowerId ID of the borrower
     * @param bookId     ID of the book to borrow
     * @return Success message
     * @throws ResourceNotFoundException If borrower or book does not exist
     * @throws BookAlreadyBorrowedException If the book is already borrowed
     */
    @Transactional
    public String borrowBook(Long borrowerId, Long bookId) {
        // Retrieve borrower or throw exception if not found
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with ID: " + borrowerId));
        // Retrieve book or throw exception if not found
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));

        // Check if the book is already borrowed
        if (book.isBorrowed()) {
            throw new BookAlreadyBorrowedException("Book with ID " + bookId + " is already borrowed.");
        }

        // Mark book as borrowed and save
        book.setBorrowed(true);
        bookRepository.save(book);

        // Create and save the borrowing record
        BorrowingRecord record = new BorrowingRecord();
        record.setBorrower(borrower);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());

        borrowingRecordRepository.save(record);

        return "Book borrowed successfully.";
    }

    /**
     * Handles the return of a borrowed book.
     *
     * @param borrowerId ID of the borrower (used for verification/future extension)
     * @param bookId     ID of the book to return
     * @return Success message
     * @throws ResourceNotFoundException If book is not found
     * @throws IllegalStateException If the book is not borrowed or no borrowing record exists
     */
    @Transactional
    public String returnBook(Long borrowerId, Long bookId) {
        // Retrieve book or throw exception if not found
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));

        // Ensure the book is currently borrowed
        if (!book.isBorrowed()) {
            throw new IllegalStateException("Book with ID " + bookId + " is not currently borrowed.");
        }

        // Mark the book as returned
        book.setBorrowed(false);
        bookRepository.save(book);

        // Find the active borrowing record and update return date
        BorrowingRecord record = borrowingRecordRepository.findAll().stream()
                .filter(r -> r.getBook().getId().equals(bookId) && r.getReturnDate() == null)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No active borrowing record found for book ID: " + bookId));

        record.setReturnDate(LocalDate.now());
        borrowingRecordRepository.save(record);

        return "Book returned successfully.";
    }
}
