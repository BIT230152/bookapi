package com.example.bookapi.services;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import com.example.bookapi.models.Book;
import com.example.bookapi.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getAllBooks(int page, int size, String title) {
        Pageable pageable = PageRequest.of(page, size);
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        }
        return bookRepository.findAll(pageable);
    }

    // create book
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // get book by id
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // delete by id
    public boolean deleteBookById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
