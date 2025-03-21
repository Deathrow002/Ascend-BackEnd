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
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublishedDate(convertToBuddhistCalendar(book.getPublishedDate())); // Convert before sending
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
        book.setPublishedDate(convertToGregorianCalendar(bookDTO.getPublishedDate())); // Convert before saving
        return book;
    }

    // Convert Gregorian date to Buddhist Calendar format (String)
    private static String convertToBuddhistCalendar(String publishedDate) {
        if (!isBuddhistDate(publishedDate)) {
            throw new IllegalArgumentException("Invalid Buddhist date format. Expected format: yyyy-MM-dd");
        }

        try {
            LocalDate date = LocalDate.parse(publishedDate, DATE_FORMATTER);
            int buddhistYear = date.getYear() + 543;
            return formatDate(date, buddhistYear);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format for Buddhist calendar. Expected format: yyyy-MM-dd", e);
        }
    }

    // Convert Buddhist Calendar date back to Gregorian Calendar format (String)
    private static String convertToGregorianCalendar(String publishedDate) {
        if (isBuddhistDate(publishedDate)) {
            return publishedDate; // If it's already a Buddhist date, return it as is
        }

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

    // Check if the input date string is a valid Buddhist date
    private static boolean isBuddhistDate(String dateStr) {
        try {
            // Try parsing the date using the Buddhist calendar format
            String[] parts = dateStr.split("-");
            if (parts.length != 3) {
                return false; // Invalid format if it's not exactly "yyyy-MM-dd"
            }

            int buddhistYear = Integer.parseInt(parts[0]);
            int gregorianYear = buddhistYear - 543;

            // Check if the year is in the valid range
            if (gregorianYear < 0) {
                return false; // Invalid Gregorian year
            }

            // Validate if the month and day are in correct range
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            // Check if the date is a valid Gregorian date
            LocalDate date = LocalDate.of(gregorianYear, month, day); // This will throw an exception if the date is invalid

            return true; // If all checks pass, it's a valid Buddhist date
        } catch (Exception e) {
            return false; // If any exception occurs, it's not a valid Buddhist date
        }
    }

    // Helper method to format the date based on the year
    private static String formatDate(LocalDate date, int year) {
        return year + "-" + String.format("%02d", date.getMonthValue()) + "-" + String.format("%02d", date.getDayOfMonth());
    }

    // Convert a list of Books to a list of BookDTOs
    public static List<BookDTO> toDTOList(List<Book> books) {
        return books.stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }
}