package com.ascend.crud.service;

import com.ascend.crud.model.Book;
import com.ascend.crud.model.DTO.BookDTO;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BookMapper {

    static Logger LOGGER = LoggerFactory.getLogger(BookMapper.class);

    // Convert a Book entity to a BookDTO
    public static BookDTO toDTO(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        // Do not convert the Gregorian date to Buddhist calendar
        bookDTO.setPublishedDate(book.getPublishedDate());
        return bookDTO;
    }

    // Convert a BookDTO to a Book entity
    public static Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            throw new IllegalArgumentException("BookDTO cannot be null");
        }

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        // Convert the Buddhist calendar date back to Gregorian before setting
        book.setPublishedDate(convertToGregorianCalendar(bookDTO.getPublishedDate()));
        return book;
    }

    // Convert Gregorian date to Buddhist Calendar format (LocalDate)
    private static LocalDate convertToBuddhistCalendar(LocalDate publishedDate) {
        if (publishedDate == null) {
            return null;
        }
        return publishedDate.plusYears(543);
    }

    // Convert Buddhist Calendar date back to Gregorian Calendar format (LocalDate)
    private static LocalDate convertToGregorianCalendar(LocalDate publishedDate) {
        if (publishedDate == null) {
            return null;
        }
        return publishedDate.minusYears(543);
    }

    // Check if the input date is in the Buddhist calendar or Gregorian calendar and convert to Buddhist if necessary
    private static LocalDate convertToBuddhistIfNecessary(LocalDate publishedDate) {
        if (publishedDate == null) {
            return null;
        }
        if (publishedDate.getYear() > 2500) {
            // Already in Buddhist calendar
            return publishedDate;
        } else {
            // Convert to Buddhist calendar
            return convertToBuddhistCalendar(publishedDate);
        }
    }

    // Convert a list of Books to a list of BookDTOs
    public static List<BookDTO> toDTOList(List<Book> books) {
        return books.stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
