package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Borrower;

import java.util.List;

public interface LibraryService {
    public Book registerBook(Book book);
    public List<Book> getAllBooks();
    public Borrower registerBorrower(Borrower borrower);
    public String borrowBook(Long borrowerId, Long bookId);
    public String returnBook(Long borrowerId, Long bookId);
}
