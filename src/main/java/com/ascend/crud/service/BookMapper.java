package com.ascend.crud.service;

import com.ascend.crud.model.Book;
import com.ascend.crud.model.DTO.BookDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    // Convert Book entity to BookDTO
    public static BookDTO toDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishedDate()
        );
    }

    // Convert BookDTO to Book entity
    public static Book toEntity(BookDTO bookDTO) {
        return new Book(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getPublishedDate()
        );
    }

    // Convert List<Book> to List<BookDTO>
    public static List<BookDTO> toDTOList(List<Book> books) {
        return books.stream().map(BookMapper::toDTO).collect(Collectors.toList());
    }
}

