package com.ascend.crud.controller;

import com.ascend.crud.model.DTO.BookDTO;
import com.ascend.crud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Get all books
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Get books by author
    @GetMapping("/author")
    public List<BookDTO> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // Add a new book
    @PostMapping
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    // Update a book
    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO);
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}


