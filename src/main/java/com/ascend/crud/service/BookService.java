package com.ascend.crud.service;

import com.ascend.crud.model.Book;
import com.ascend.crud.model.DTO.BookDTO;
import com.ascend.crud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get all books (returns List of DTOs)
    public List<BookDTO> getAllBooks() {
        return BookMapper.toDTOList(bookRepository.findAll());
    }

    // Get books by author (returns List of DTOs)
    public List<BookDTO> getBooksByAuthor(String author) {
        return BookMapper.toDTOList(bookRepository.findByAuthor(author));
    }

    // Get a book by ID (returns DTO)
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // Add a new book (accepts DTO, returns DTO)
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        return BookMapper.toDTO(bookRepository.save(book));
    }

    // Update a book (accepts DTO, returns DTO)
    public BookDTO updateBook(Long id, BookDTO updatedBookDTO) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBookDTO.getTitle());
                    book.setAuthor(updatedBookDTO.getAuthor());
                    book.setPublishedDate(updatedBookDTO.getPublishedDate());
                    return BookMapper.toDTO(bookRepository.save(book));
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // Delete a book by ID
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
