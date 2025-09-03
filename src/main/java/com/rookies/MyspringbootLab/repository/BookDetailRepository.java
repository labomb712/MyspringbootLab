package com.rookies.MyspringbootLab.repository;

import com.rookies.MyspringbootLab.entity.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {

    Optional<BookDetail> findByBookId(Long bookId);

    @Query("SELECT bd FROM BookDetail bd LEFT JOIN FETCH bd.book WHERE bd.id = :id")
    Optional<BookDetail> findByIdWithBook(Long id);

    @Query("SELECT bd FROM BookDetail bd WHERE bd.publisher = :publisher")
    List<BookDetail> findByPublisher(String publisher);

}