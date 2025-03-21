package com.ascend.crud.service;

import com.ascend.crud.model.Book;
import com.ascend.crud.model.DTO.BookDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Convert a Book entity to a BookDTO
    public static BookDTO toDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublishedDate(convertToBuddhistCalendar(book.getPublishedDate())); // Convert before sending
        return bookDTO;
    }

    // Convert a BookDTO to a Book entity
    public static Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublishedDate(convertToGregorianCalendar(bookDTO.getPublishedDate())); // Convert before saving
        return book;
    }

    // Convert Gregorian date to Buddhist Calendar format (String)
    private static String convertToBuddhistCalendar(String publishedDate) {
        try {
            LocalDate date = LocalDate.parse(publishedDate, DATE_FORMATTER);
            int buddhistYear = date.getYear() + 543;
            return buddhistYear + "-" + String.format("%02d", date.getMonthValue()) + "-" + String.format("%02d", date.getDayOfMonth());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd", e);
        }
    }

    // Convert Buddhist Calendar date back to Gregorian Calendar format (String)
    private static String convertToGregorianCalendar(String publishedDate) {
        try {
            String[] parts = publishedDate.split("-");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
            }
            int buddhistYear = Integer.parseInt(parts[0]);
            int gregorianYear = buddhistYear - 543;
            return gregorianYear + "-" + parts[1] + "-" + parts[2]; // Ensure correct format
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Buddhist date format. Expected format: yyyy-MM-dd", e);
        }
    }

    // Convert a list of Books to a list of BookDTOs
    public static List<BookDTO> toDTOList(List<Book> books) {
        return books.stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
