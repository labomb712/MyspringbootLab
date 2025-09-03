package com.rookies.MyspringbootLab.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publishers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Publisher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "established_date")
    private LocalDate establishedDate;

    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publisher", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        book.setPublisher(this);
    }
    public void removeBook(Book book) {
        books.remove(book);
        book.setPublisher(null);
    }
}