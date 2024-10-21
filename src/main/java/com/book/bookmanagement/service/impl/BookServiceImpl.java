package com.book.bookmanagement.service.impl;

import com.book.bookmanagement.entity.Book;
import com.book.bookmanagement.exception.ResourceNotFoundException;
import com.book.bookmanagement.repository.BookRepository;
import com.book.bookmanagement.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @Override
    public Book updateBook(Long bookId, Book updatedBook) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setTitle(updatedBook.getTitle() == null ? book.getTitle() : updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor() == null ? book.getAuthor() : updatedBook.getAuthor());
        book.setGenre(updatedBook.getGenre() == null ? book.getGenre() : updatedBook.getGenre());
        book.setDescription(updatedBook.getDescription() == null ? book.getDescription() : updatedBook.getDescription());
        book.setTotalCopies(updatedBook.getTotalCopies() == null ? book.getTotalCopies() : updatedBook.getTotalCopies());
        book.setAvailableCopies(updatedBook.getAvailableCopies() == null ? book.getAvailableCopies() : updatedBook.getAvailableCopies());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
