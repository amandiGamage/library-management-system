package com.example.library.service;

import com.example.library.model.Book;

import java.util.List;

public interface LibraryService {
    public Book registerBook(Book book);
    public List<Book> getAllBooks();
}
