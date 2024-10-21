package com.book.bookmanagement.service;

import com.book.bookmanagement.entity.Book;

import java.util.List;

public interface BookService {

    Book addBook(Book book);

    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book updateBook(Long bookId, Book updatedBook);

    void deleteBook(Long id);
}
