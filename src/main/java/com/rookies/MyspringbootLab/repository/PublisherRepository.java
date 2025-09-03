package com.rookies.MyspringbootLab.repository;

import com.rookies.MyspringbootLab.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    public Optional<Publisher> findByName(String name);
    @Query("SELECT p FROM Publisher p LEFT JOIN FETCH p.books WHERE p.id = :id")
    public Optional<Publisher> findByIdWithBooks(@Param("id") Long id);
    public boolean existsByName(String name);
}