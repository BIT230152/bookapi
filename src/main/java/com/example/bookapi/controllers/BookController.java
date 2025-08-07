package com.example.bookapi.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bookapi.models.Book;
import com.example.bookapi.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String title) {
        return bookService.getAllBooks(page, size, title);
    }

    // create a book
    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    // update book by id
    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = bookService.getBookById(id);
        if (book == null)
            return ResponseEntity.notFound().build();

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());

        return ResponseEntity.ok(bookService.saveBook(book));
    }

    // delete book by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBookById(id);
        return deleted ? ResponseEntity.ok().body("Delete success") : ResponseEntity.notFound().build();
    }
}
