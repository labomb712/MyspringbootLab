package com.rookies.MyspringbootLab.repository;
import com.rookies.MyspringbootLab.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
}