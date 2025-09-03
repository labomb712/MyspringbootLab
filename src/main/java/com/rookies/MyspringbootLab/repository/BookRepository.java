package com.rookies.MyspringbootLab.repository;

import com.rookies.MyspringbootLab.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    //findByIsbn(String isbn) , findByAuthor(String author) 등의 메소드 정의 가능


    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthor(String author);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookDetail WHERE b.id = :id")
    Optional<Book> findByIdWithBookDetail(@Param("id") Long id);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookDetail WHERE b.isbn = :isbn")
    Optional<Book> findByIsbnWithBookDetail(String isbn);

    boolean existsByIsbn(String isbn);

    List<Book> findByPublisherId(Long publisherId);

    @Query("SELECT COUNT(b) FROM Book b WHERE b.publisher.id = :publisherId")
    Long countByPublisherId(@Param("publisherId") Long publisherId);

}