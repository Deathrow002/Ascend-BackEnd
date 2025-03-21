package com.ascend.crud.service;

import com.ascend.crud.model.Book;
import com.ascend.crud.model.DTO.BookDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    // Convert a Book entity to a BookDTO
    public static BookDTO toDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublishedDate(convertToBuddhistCalendar(book.getPublishedDate()));
        return bookDTO;
    }

    // Convert a BookDTO to a Book entity
    public static Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublishedDate(convertToGregorianCalendar(bookDTO.getPublishedDate()));
        return book;
    }

    // Convert Gregorian date to Buddhist Calendar
    private static String convertToBuddhistCalendar(String publishedDate) {
        if (publishedDate == null || !publishedDate.contains("-")) {
            throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
        }

        String[] parts = publishedDate.split("-");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
        }

        try {
            int gregorianYear = Integer.parseInt(parts[0]);
            int buddhistYear = gregorianYear + 543;
            return buddhistYear + "-" + parts[1] + "-" + parts[2]; // Ensure proper formatting
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Year must be a valid number", e);
        }
    }

    // Convert Buddhist Calendar back to Gregorian Calendar
    private static String convertToGregorianCalendar(String publishedDate) {
        String[] parts = publishedDate.split("-");
        int buddhistYear = Integer.parseInt(parts[0]);
        int gregorianYear = buddhistYear - 543;
        return gregorianYear + parts[1] + parts[2];  // Return in format yyyy-MM-dd
    }

    // Convert a list of Books to a list of BookDTOs
    public static List<BookDTO> toDTOList(List<Book> books) {
        return books.stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
