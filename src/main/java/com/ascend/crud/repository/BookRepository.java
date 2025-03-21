package com.ascend.crud.repository;

import com.ascend.crud.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);  // Custom query method
}
